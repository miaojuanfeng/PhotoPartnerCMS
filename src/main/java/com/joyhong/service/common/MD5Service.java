package com.joyhong.service.common;

import java.security.MessageDigest;

import org.springframework.stereotype.Service;

@Service
public class MD5Service {
	public String encryptMD5(String data) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] b = data.getBytes("utf-8");
			byte[] btInput = b;
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}




//public class MD5Service {
//	/** 
//     * 对数据，进行MD5算法的信息摘要计算 
//     *  
//     * @param data 
//     *            数据的字节数组 
//     * @return 
//     */  
//    public String encryptMD5(byte[] data) {  
//        try {  
//            // 判断数据的合法性  
//            if (data == null) {  
//                throw new RuntimeException("数据不能为NULL");  
//            }  
//            // 获取MD5算法  
//            MessageDigest md5 = MessageDigest.getInstance("MD5");  
//            // 加入要获取摘要的数据  
//            md5.update(data);  
//            // 获取数据的信息摘要  
//            byte[] resultBytes = md5.digest();  
//            // 将字节数组转化为16进制  
//            String resultString = fromBytesToHex(resultBytes);  
//            return resultString;  
//        } catch (Exception e) {  
//            throw new RuntimeException(e);  
//        }  
//  
//    }  
//  
//    /** 
//     * 对数据，进行MD5算法的信息摘要计算 
//     *  
//     * @param data 
//     *            要进行计算信息摘要的数据 
//     * @return 
//     */  
//    public String encryptMD5(String data) {  
//        try {  
//            // 判断数据的合法性  
//            if (data == null) {  
//                throw new RuntimeException("数据不能为NULL");  
//            }  
//            // 获取MD5算法  
//            MessageDigest md5 = MessageDigest.getInstance("MD5");  
//            // 加入要获取摘要的数据  
//            md5.update(data.getBytes());  
//            // 获取数据的信息摘要  
//            byte[] resultBytes = md5.digest();  
//            // 将字节数组转化为16进制  
//            String resultString = fromBytesToHex(resultBytes);  
//            return resultString;  
//        } catch (Exception e) {  
//            throw new RuntimeException(e);  
//        }  
//  
//    }  
//  
//    /** 
//     * 计算给定文件的信息摘要 
//     *  
//     * @param path 
//     *            文件的路径 
//     * @return 
//     * @throws Exception 
//     */  
//    public String getMD5OfFile(String path) {  
//        FileInputStream fis = null;  
//        DigestInputStream dis = null;  
//        String resultString = null;  
//        try {  
//            fis = new FileInputStream(new File(path));  
//            dis = new DigestInputStream(fis, MessageDigest.getInstance("MD5"));  
//            // 流输入  
//            byte[] buffer = new byte[1024];  
//            int read = dis.read(buffer, 0, 1024);  
//            while (read != -1) {  
//                read = dis.read(buffer, 0, 1024);  
//            }  
//  
//            MessageDigest md = dis.getMessageDigest();  
//            byte[] resultBytes = md.digest();  
//            // 将字节数组转化为16进制  
//            resultString = fromBytesToHex(resultBytes);  
//        } catch (Exception e) {  
//            throw new RuntimeException(e);  
//        } finally {  
//            // 关闭流，释放资源  
//            CloseUtils.close(fis, dis);  
//        }  
//        return resultString;  
//    }  
//  
//    /** 
//     * 对数据，进行MD5算法的信息摘要计算，加入了salt 
//     *  
//     * @param data 
//     *            数据的字节数组 
//     * @param salt 
//     *            加入的盐 
//     * @return 
//     */  
//    public String encryptMD5AndSalt(byte[] data, Object salt) {  
//        try {  
//            // 将data和盐拼接  
//            String datatemp = new String(data);  
//            String data_salt = mergeDataAndSalt(datatemp, salt);  
//            // 加入盐后，数据的信息摘要  
//            // 获取MD5算法  
//            MessageDigest md5 = MessageDigest.getInstance("MD5");  
//            // 加入要获取摘要的数据  
//            md5.update(data_salt.getBytes());  
//            // 获取数据的信息摘要  
//            byte[] resultBytes = md5.digest();  
//            // 将字节数组转化为16进制  
//            String resultString = fromBytesToHex(resultBytes);  
//            return resultString;  
//        } catch (Exception e) {  
//            throw new RuntimeException(e);  
//        }  
//  
//    }  
//  
//    /** 
//     * 对数据，进行MD5算法的信息摘要计算，加入了salt 
//     *  
//     * @param data 
//     *            数据的字节数组 
//     * @param salt 
//     *            加入的盐 
//     * @return 
//     */  
//    public String encryptMD5AndSalt(String data, Object salt) {  
//        try {  
//            // 完成数据和盐的拼接  
//            String data_salt = mergeDataAndSalt(data, salt);  
//            // 加入盐后，数据的信息摘要  
//            // 获取MD5算法  
//            MessageDigest md5 = MessageDigest.getInstance("MD5");  
//            // 加入要获取摘要的数据  
//            md5.update(data_salt.getBytes());  
//            // 获取数据的信息摘要  
//            byte[] resultBytes = md5.digest();  
//            // 将字节数组转化为16进制  
//            String resultString = fromBytesToHex(resultBytes);  
//            return resultString;  
//        } catch (Exception e) {  
//            throw new RuntimeException(e);  
//        }  
//  
//    }  
//  
//    /** 
//     * 用于数据和salt的拼接 
//     *  
//     * @param data 
//     *            要计算数据摘要的数据 
//     * @param salt 
//     *            加入的盐 
//     * @return 
//     */  
//    private String mergeDataAndSalt(String data, Object salt) {  
//        if (data == null) {  
//            data = "";  
//        }  
//  
//        if ((salt == null) || "".equals(salt)) {  
//            return data;  
//        } else {  
//            return data + "{" + salt.toString() + "}";  
//        }  
//  
//    }  
//  
//    /** 
//     *  
//     * @param encPass 
//     *            加入盐后，计算的数据摘要 
//     * @param rawPass 
//     *            加盐前的数据 
//     * @param salt 
//     *            要加入的盐 
//     * @return 
//     */  
//    public boolean isPasswordValid(String encPass, String rawPass,  
//            Object salt) {  
//        String data1 = encPass;  
//        String data2 = encryptMD5AndSalt(rawPass, salt);  
//        return data2.equals(data1);  
//    }  
//  
//    /** 
//     * 将给定的字节数组，转化为16进制数据 
//     *  
//     * @param resultBytes 
//     * @return 
//     */  
//    private String fromBytesToHex(byte[] resultBytes) {  
//        StringBuilder builder = new StringBuilder();  
//        for (int i = 0; i < resultBytes.length; i++) {  
//            if (Integer.toHexString(0xFF & resultBytes[i]).length() == 1) {  
//                builder.append("0").append(  
//                        Integer.toHexString(0xFF & resultBytes[i]));  
//            } else {  
//                builder.append(Integer.toHexString(0xFF & resultBytes[i]));  
//            }  
//        }  
//        return builder.toString();  
//    }
//}
//
//class CloseUtils {  
//    public static void close(Closeable... closeables) {  
//        for (int i = 0; i < closeables.length; i++) {  
//            if (closeables[i] != null) {  
//                try {  
//                    closeables[i].close();  
//                } catch (IOException e) {  
//                    e.printStackTrace();  
//                }  
//            }  
//        }  
//    }  
//}
