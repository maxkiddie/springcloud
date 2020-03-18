/**
 * 
 */
package com.ydy.utils;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author nothing
 *
 *         2018年8月3日 下午3:47:24
 */
public class ImageCompressUtil {
	/**
	 * 按照固定宽高原图压缩
	 *
	 * @param img    源图片文件
	 * @param width  宽
	 * @param height 高
	 * @param out    输出流
	 * @throws IOException the io exception
	 */
	public static void thumbnail(String img, String out, String last) throws IOException {
		File file = new File(img);
		BufferedImage bufferedImage = ImageIO.read(file);
		Image image = bufferedImage.getScaledInstance(bufferedImage.getWidth(), bufferedImage.getHeight(),
				Image.SCALE_SMOOTH);
		BufferedImage tag = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(),
				BufferedImage.TYPE_INT_RGB);
		Graphics graphics = tag.getGraphics();
		graphics.setColor(Color.RED);
		// 绘制处理后的图
		graphics.drawImage(image, 0, 0, null);
		graphics.dispose();
		FileOutputStream outputStream = new FileOutputStream(new File(out));
		ImageIO.write(tag, last, outputStream);
	}

	/**
	 * 按照固定宽高原图压缩
	 *
	 * @param img    源图片文件
	 * @param width  宽
	 * @param height 高
	 * @param out    输出流
	 * @throws IOException the io exception
	 */
	public static byte[] thumbnail(byte[] bytes, String last) throws IOException {
		ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
		BufferedImage bufferedImage = ImageIO.read(inputStream);
		Image image = bufferedImage.getScaledInstance(bufferedImage.getWidth(), bufferedImage.getHeight(),
				Image.SCALE_SMOOTH);
		BufferedImage tag = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(),
				BufferedImage.TYPE_INT_RGB);
		Graphics graphics = tag.getGraphics();
		graphics.setColor(Color.RED);
		// 绘制处理后的图
		graphics.drawImage(image, 0, 0, null);
		graphics.dispose();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ImageIO.write(tag, last, outputStream);
		return outputStream.toByteArray();
	}

}
