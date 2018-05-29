package com.joyhong.cms;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.joyhong.model.Device;
import com.joyhong.model.Order;
import com.joyhong.service.DeviceService;
import com.joyhong.service.OrderService;
import com.joyhong.service.common.FuncService;

@Controller
@RequestMapping("cms/order")
public class OrderCtrl {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private DeviceService deviceService;
	
	@Autowired
	private FuncService funcService;
	
	@RequestMapping(value="/select", method=RequestMethod.GET)
	public String select(){
		return "redirect:/cms/order/select/1";
	}
	
	@RequestMapping(value="/select/{page}", method=RequestMethod.GET)
	public String select(
			Model model,  
			@PathVariable(value="page") Integer page
	){
		int pageSize = 20;
		int totalRecord = orderService.selectCount();
		int totalPage = (int)Math.ceil((double)totalRecord/pageSize);
		
		if( page < 1 || page > totalPage ){
			page = 1;
		}
		
		Integer offset = (page-1)*pageSize;
		List<Order> order = orderService.selectOffsetAndLimit(offset, pageSize);
		
		model.addAttribute("page", page);
		model.addAttribute("totalPage", totalPage);
		model.addAttribute("totalRecord", totalRecord);
		model.addAttribute("order", order);
		
		return "OrderView";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public String insert(
			Model model
	){
		model.addAttribute("order", new Order());

		return "OrderView";
	}
	
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(
			Model model, 
			@ModelAttribute("order") Order order, 
			@RequestParam("referer") String referer
	){
		if( orderService.insert(order) == 1 ){
			if( referer != "" ){
				return "redirect:"+referer.substring(referer.lastIndexOf("/cms/"));
			}
			return "redirect:/cms/order/select";
		}
		
		return "OrderView";
	}
	
	@RequestMapping(value="/update/{order_id}", method=RequestMethod.GET)
	public String update(
			Model model, 
			@PathVariable("order_id") Integer order_id
	){
		Order order = orderService.selectByPrimaryKey(order_id);
		if( order != null ){
			model.addAttribute("order", order);
			
			List<Device> device = deviceService.selectByOrderId(order.getId());
			model.addAttribute("device", device);
			model.addAttribute("deviceTotal", device.size());
		
			return "OrderView";
		}
		return "redirect:/cms/order/select";
	}
	
	@RequestMapping(value="/update/{order_id}", method=RequestMethod.POST)
	public String update(
			Model model, 
			HttpSession httpSession, 
			HttpServletRequest request, 
			@PathVariable("order_id") Integer order_id, 
			@ModelAttribute("order") Order order, 
			@RequestParam("referer") String referer
	){
		order.setId(order_id);
		order.setModifyDate(new Date());
		order.setDeleted(0);
		if( orderService.updateByPrimaryKeyWithBLOBs(order) == 1 ){
			if( referer != "" ){
				return "redirect:"+referer.substring(referer.lastIndexOf("/cms/"));
			}
			return "redirect:/cms/order/select";
		}
		
		return "OrderView";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST)
	public String delete(
			Model model, 
			@RequestParam("order_id") Integer order_id
	){
		Order order = orderService.selectByPrimaryKey(order_id);
		if( order != null ){
			order.setModifyDate(new Date());
			order.setDeleted(1);
			orderService.updateByPrimaryKey(order);
		}
		
		return "redirect:/cms/order/select";
	}
	
	@RequestMapping(value="/generate", method=RequestMethod.POST)
	public String generate(
			Model model, 
			@RequestParam("order_id") Integer order_id
	){
		Order order = orderService.selectByPrimaryKey(order_id);
		if( order != null ){
			String order_code = order.getOrderCode();
			int order_qty = order.getOrderQty();
			List<String> exist_device = deviceService.selectByOrderIdReturnDeviceToken(order_id);
			int i = 0;
			for(i=0; i<order_qty; i++){
				int random_number = (int)((Math.random()*9+1)*100000);
				String device_token = order_code + random_number;
				//判断是否已经存在该device_token;
				if( exist_device != null && exist_device.contains(device_token) ){
					i--;
					continue;
				}
				Device device = new Device();
				device.setOrderId(order_id);
				device.setDeviceToken(device_token);
				device.setDeviceFcmToken("");
				device.setCreateDate(new Date());
				device.setModifyDate(new Date());
				device.setDeleted(0);
			
				deviceService.insert(device);
			}
		}
		
		return "redirect:/cms/order/update/"+order_id;
	}
	
	@ModelAttribute
	public void startup(Model model, HttpSession httpSession, HttpServletRequest request){
		funcService.modelAttribute(model, httpSession, request);
	}
}
