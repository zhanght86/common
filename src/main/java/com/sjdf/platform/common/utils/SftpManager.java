package com.sjdf.platform.common.utils;

import com.jcraft.jsch.*;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.sjdf.platform.log.logger.SJDFLogger;
import com.sjdf.platform.log.logger.SJDFLoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;
import java.util.Vector;

/**
 * 2012-12-26 下午4:44:41
 * 对远程sftp服务器进行操作,依赖jsch.jar,包括 文件上传,下载,删除等操作
 *
 * @author ketqi
 */
public class SftpManager {
    private SJDFLogger logger = SJDFLoggerFactory.getSJDFLogger(SftpManager.class);
    private ChannelSftp sftp;
    /** 当前操作的目录 */
    private String directory = "/";

    /**
     * @param host      主机名或者Ip地址
     * @param port      端口号
     * @param username  用户名
     * @param password  密码
     * @param directory 当前操作的目录
     */
    public SftpManager(String host, int port, String username, String password, String directory) {
        this.directory = directory;
        this.sftp = init(host, port, username, password);
    }

    /**
     * 连接sftp服务器
     *
     * @param host     主机
     * @param port     端口
     * @param username 用户名
     * @param password 密码
     */
    private ChannelSftp init(String host, int port, String username, String password) {
        ChannelSftp channelSftp;
        try {
            JSch jsch = new JSch();
            jsch.getSession(username, host, port);
            Session sshSession = jsch.getSession(username, host, port);
            logger.info("Session created.");

            sshSession.setPassword(password);
            Properties sshConfig = new Properties();
            sshConfig.put("StrictHostKeyChecking", "no");
            sshSession.setConfig(sshConfig);
            sshSession.connect();
            logger.info("Session connected.");

            Channel channel = sshSession.openChannel("sftp");
            channel.connect();
            channelSftp = (ChannelSftp) channel;
            logger.info("Opening Channel.Connected to " + host + ".");
        } catch (Exception e) {
            logger.error("connect sftp fail", e);
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return channelSftp;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    /**
     * 上传文件,基于远程directory
     *
     * @param uploadFile 要上传的文件
     */
    public void upload(String uploadFile) {
        try {
            sftp.cd(directory);
            File file = new File(uploadFile);
            sftp.put(new FileInputStream(file), file.getName());
        } catch (Exception e) {
            logger.error("upload " + uploadFile + " fail!", e);
            e.printStackTrace();
        }
    }

    /**
     * 下载文件
     *
     * @param srcFileName 下载的文件名称,基于远程directory
     * @param dstFilePath 存在本地的路径
     */
    public void download(String srcFileName, String dstFilePath) {
        try {
            sftp.cd(directory);
            sftp.get(srcFileName, new FileOutputStream(new File(dstFilePath)));
        } catch (Exception e) {
            logger.error("download " + srcFileName + " fail!", e);
            e.printStackTrace();
        }
    }

    /**
     * 删除文件
     *
     * @param srcFileName 要删除的文件名称
     */
    public void delete(String srcFileName) {
        try {
            sftp.cd(directory);
            sftp.rm(srcFileName);
        } catch (Exception e) {
            logger.error("delete " + srcFileName + " fail!", e);
            e.printStackTrace();
        }
    }

    /**
     * 列出目录下的文件
     *
     * @return Vector<LsEntry>
     */
    @SuppressWarnings("unchecked")
    public Vector<LsEntry> ls() {
        try {
            return (Vector<LsEntry>) sftp.ls(directory);
        } catch (SftpException e) {
            logger.error("ls " + directory + " fail!", e);
            e.printStackTrace();
        }
        return new Vector<>();
    }
}