//package com.imobpay.base.util;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.InputStreamReader;
//
//import org.apache.commons.io.IOUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import sun.net.TelnetInputStream;
//import sun.net.TelnetOutputStream;
//import sun.net.ftp.FtpClient;
//
///**
// * FTP工具类
// * <p>
// * 提供FTP文件列表、上传、下载功能
// * 
// * @author 杜忠(Michael.Du)
// * @version 1.0
// * @since 1.5
// */
//
//public class FtpUtil {
//    /**
//     * 获取类日志对象
//     */
//    private static Logger log = LoggerFactory.getLogger(FtpUtil.class);
//
//    /**
//     * FTP文件列表
//     * 
//     * @param server
//     *            服务器地址
//     * @param user
//     *            用户名
//     * @param password
//     *            密码
//     * @param path
//     *            路径
//     * @return String 文件列表返回（未解析）
//     * @throws IOException
//     *             抛出IO错误
//     */
//    public static String ftpList(String server, String user, String password, String path) throws IOException {
//        StringBuffer out = new StringBuffer();
//        /**
//         * 1、 创建FtpClient对象 2、连接FTP服务器 3、登录FTP服务器
//         */
//        FtpClient ftpClient = new FtpClient();
//
//        ftpClient.openServer(server);
//
//        ftpClient.login(user, password);
//
//        if (path.length() != 0) {
//            ftpClient.cd(path);
//        }
//
//        TelnetInputStream is = ftpClient.list();
//        int c;
//        while ((c = is.read()) != -1) {
//            out.append((char) c);
//        }
//        is.close();
//        ftpClient.closeServer(); // 退出FTP服务器
//
//        return out.toString();
//    }
//
//    /**
//     * FTP下载
//     * 
//     * @param server
//     *            服务器地址
//     * @param user
//     *            用户名
//     * @param password
//     *            密码
//     * @param path
//     *            路径
//     * @param filename
//     *            文件名
//     * @param targetPath
//     *            保存目标路径
//     * @throws IOException
//     *             抛出IO错误
//     */
//    public static void ftpGet(String server, String user, String password, String path, String filename, String targetPath) throws IOException {
//        FtpClient ftpClient = new FtpClient();
//        ftpClient.openServer(server);
//        ftpClient.login(user, password);
//        if (path.length() != 0) {
//            ftpClient.cd(path);
//        }
//
//        ftpClient.binary();
//        TelnetInputStream is = ftpClient.get(filename);
//        File fileOut = new File(targetPath + filename);
//        FileOutputStream os = new FileOutputStream(fileOut);
//        byte[] bytes = new byte[1024];
//        int c;
//        while ((c = is.read(bytes)) != -1) {
//            os.write(bytes, 0, c);
//        }
//        is.close();
//        os.close();
//        ftpClient.closeServer();
//    }
//
//    /**
//     * FTP下载
//     * 
//     * @param server
//     *            服务器地址
//     * @param user
//     *            用户名
//     * @param password
//     *            密码
//     * @param path
//     *            路径
//     * @param filename
//     *            文件名
//     * @param targetPath
//     *            保存目标路径
//     * @param port
//     *            端口号
//     * 
//     * 
//     * @throws IOException
//     *             抛出IO错误
//     */
//    public static void ftpGet2(String server, int port, String user, String password, String path, String filename, String targetPath) throws IOException {
//        FtpClient ftpClient = new FtpClient();
//        ftpClient.openServer(server, port);
//        ftpClient.login(user, password);
//        if (path.length() != 0) {
//            ftpClient.cd(path);
//        }
//
//        ftpClient.binary();
//        TelnetInputStream is = ftpClient.get(filename);
//        // 创建字符输入流
//        BufferedReader br = new BufferedReader(new InputStreamReader(is, "GBK"));
//        String data = null;
//        // 读取输入流中的文件目录
//        while ((data = br.readLine()) != null) {
//            // 8:交易日期
//            log.info(data);
//            // System.out.println(data);
//        }
//        is.close();
//        ftpClient.closeServer();
//    }
//
//    /**
//     * FTP上传
//     * 
//     * @param server
//     *            服务器地址
//     * @param user
//     *            用户名
//     * @param password
//     *            密码
//     * @param path
//     *            路径
//     * @param filename
//     *            文件名
//     * @param sourcePath
//     *            源路径
//     * @param port
//     *            端口号
//     */
//    public static void ftpPut2(String server, int port, String user, String password, String path, String filename, String sourcePath) {
//        try {
//            FtpClient ftpClient = new FtpClient();
//            ftpClient.openServer(server, port);
//            ftpClient.login(user, password);
//            if (path.length() != 0) {
//                ftpClient.cd(path);
//            }
//
//            // binary 传输方式不会自动换行
//            // ftpClient.binary();
//            // ascii 传输方式会自动换行
//            ftpClient.ascii();
//            TelnetOutputStream os = ftpClient.put(filename);
//            File fileIn = new File(sourcePath + filename);
//            FileInputStream is = new FileInputStream(fileIn);
//            byte[] bytes = new byte[1024];
//            int c;
//            while ((c = is.read(bytes)) != -1) {
//                os.write(bytes, 0, c);
//            }
//            is.close();
//            os.close();
//            ftpClient.closeServer();
//        } catch (Exception e) {
//            log.info(e.getMessage(), e);
//            log.error(e.getMessage(), e);
//            log.info("FTP上传异常。");
//            // System.out.println("FTP上传异常。");
//        }
//    }
//
//    /**
//     * 
//     * @param server
//     *            服务器地址
//     * @param port
//     *            服务器断开
//     * @param user
//     *            用户名
//     * @param password
//     *            密码
//     * @param targetPath
//     *            目标地址
//     * @param sourcePath
//     *            源路径
//     * @param fileName
//     *            文件名
//     * @return boolean 返回结果
//     */
//    public static boolean FtpPic(String server, int port, String user, String password, String targetPath, String sourcePath, String fileName) {
//        // 是否开启FTP上传功能
//        FtpClient ftpClient = new FtpClient();
//        TelnetOutputStream os = null;
//        try {
//            ftpClient.openServer(server, port);
//            ftpClient.login(user, password);
//            createDir(ftpClient, targetPath);
//            ftpClient.cd("/");
//            if (targetPath.length() != 0) {
//                ftpClient.cd(targetPath);
//            }
//
//            ftpClient.binary();
//            os = ftpClient.put(fileName);
//            byte[] b = Format.decryptBASE64(sourcePath);
//            os.write(b);
//        } catch (IOException e) {
//            log.info(e.getMessage(), e);
//            log.error(e.getMessage(), e);
//            return false;
//        } finally {
//            try {
//                os.close();
//                ftpClient.closeServer();
//            } catch (IOException e) {
//                log.info(e.getMessage(), e);
//                log.error(e.getMessage(), e);
//                return false;
//            }
//        }
//        return true;
//
//    }
//
//    /**
//     * @param ftpClient
//     *            ftp客户端
//     * @param path
//     *            地址参数
//     * @return 返回结果
//     * @throws IOException
//     */
//    public static boolean createDir(FtpClient ftpClient, String path) {
//        if (path.length() > 0) {
//            String[] str = path.split("/");
//            for (int i = 0; i < str.length; i++) {
//                try {
//                    String pathName = ftpClient.pwd();
//                    if (!isExitDirectory(ftpClient, str[i])) {
//                        ftpClient.sendServer("MKD " + pathName + "/" + str[i] + "\r\n");
//                    } else {
//                        continue;
//                    }
//                    ftpClient.readServerResponse();
//                    ftpClient.cd(str[i]);
//                } catch (Exception e) {
//                    log.info(e.getMessage(), e);
//                    log.error(e.getMessage(), e);
//                    return false;
//                }
//
//            }
//            return true;
//        } else {
//            return false;
//        }
//
//    }
//
//    /**
//     * 
//     * @param ftpClient
//     *            ftp客户端
//     * @param pathExit
//     *            文件路径
//     * @return boolean
//     */
//    public static boolean isExitDirectory(FtpClient ftpClient, String pathExit) {
//        try {
//            ftpClient.cd(pathExit);
//        } catch (Exception e) {
//            return false;
//        }
//        return true;
//    }
//
//    /**
//     * FTP上传
//     * 
//     * @param server
//     *            服务器地址
//     * @param user
//     *            用户名
//     * @param password
//     *            密码
//     * @param path
//     *            路径
//     * @param filename
//     *            文件名
//     * @param sourcePath
//     *            源路径
//     * @throws IOException
//     *             抛出IO错误
//     */
//    public static void ftpPut(String server, String user, String password, String path, String filename, String sourcePath) throws IOException {
//
//        FtpClient ftpClient = new FtpClient();
//        ftpClient.openServer(server, 21); // 11003
//        ftpClient.login(user, password);
//        createDir(ftpClient, path);
//        ftpClient.binary();
//        TelnetOutputStream os = ftpClient.put("2.txt");
//        File fileIn = new File(sourcePath + filename);
//        FileInputStream is = new FileInputStream(fileIn);
//        byte[] bytes = new byte[1014];
//        int c;
//        while ((c = is.read(bytes)) != -1) {
//            os.write(bytes, 0, c);
//        }
//        log.info("0k....");
//        // System.out.println("0k....");
//        os.flush();
//        is.close();
//        os.close();
//        ftpClient.closeServer();
//    }
//
//    /**
//     * 获取ftp图片
//     * 
//     * @param server
//     *            服务器地址
//     * @param user
//     *            用户名
//     * @param password
//     *            密码
//     * @param path
//     *            路径
//     * @param filename
//     *            文件名
//     * @param port
//     *            端口号
//     * @return String
//     * @throws IOException
//     * 
//     */
//    public static String ftpGetPic(String server, int port, String user, String password, String path, String filename) {
//        FtpClient ftpClient = new FtpClient();
//        TelnetInputStream is = null;
//        String result = "";
//        try {
//            ftpClient.openServer(server, port);
//            ftpClient.login(user, password);
//            if (path.length() != 0) {
//                ftpClient.cd(path);
//            }
//
//            ftpClient.binary();
//            is = ftpClient.get(filename);
//            byte[] bytes = IOUtils.toByteArray(is);
//            result = Format.bytesToHexString(bytes);
//        } catch (Exception e) {
//            log.info(e.getMessage(), e);
//            log.error(e.getMessage(), e);
//            result = "";
//        } finally {
//            try {
//                if (is != null) {
//                    is.close();
//                }
//                if (ftpClient != null) {
//                    ftpClient.closeServer();
//
//                }
//            } catch (IOException e) {
//                log.info(e.getMessage(), e);
//                log.error(e.getMessage(), e);
//            }
//
//        }
//        return result;
//    }
//
//    /*
//     * public static void main(String[] args) { String url =
//     * "http://pic.imobpay.com:10080/00800060/A000015000/A000015165/TRAN/1425457553211A000015165.png"
//     * ; String url2 =
//     * "http://pic.imobpay.com:10080/00800021/A000013000/A000013178/TRAN/1425535854472_2014091856070919.png"
//     * ; System.out.println(url2.substring(28, 65) + "==" + url2.substring(65));
//     * 
//     * System.out.println(url.substring(28, 65) + "==" + url.substring(65));
//     * System.out.println(System.currentTimeMillis()); //
//     * ftpGetPic("192.168.1.7", 21, "qwer", "qwer", //
//     * "/11000003/A000000000/A000000721/TRAN/", //
//     * "1422341413754A000000721.png", "D:\\"); // ftpGetPic("192.168.1.7", 21,
//     * "qwer", "qwer", // "/11000003/A000000000/A000000721/TRAN/", //
//     * "1422341413754A000000721.png", "D:\\"); // ftpGetPic("192.168.1.7", 21,
//     * "qwer", "qwer", // "/11000003/A000000000/A000000721/TRAN/", //
//     * "1422341413754A000000721.png", "D:\\");
//     * System.out.println(ftpGetPic("192.168.1.7", 21, "qwer", "qwer",
//     * "/111000003/A000000000/A000000721/TRAN/",
//     * "1422341413754A000000721.png"));
//     * 
//     * System.out.println(System.currentTimeMillis()); // 58.246.227.98 //
//     * ftpGet2("192.168.1.127", 21, "qwer", "qwer", //
//     * "/CCB_EBSClient_B2BV5.0Build20131102/download/", //
//     * "2014101697655350501_d", "D:\\"); // 9001 127 9002__>177 //
//     * ftpPut("192.168.1.7", "qwer", "qwer", "1/2/3", "1.txt", "D:\\"); //
//     * ftpPut2
//     * ("192.168.1.127",21,"administrator","qt123456","/","1.txt","D:\\"); //
//     * ftpPut2("192.168.1.177", 21, "qwer", "qwer", "/", "1.txt", "D:\\");
//     * 
//     * }
//     */
//}
