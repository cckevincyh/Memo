package indi.cc.memo.util;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.JOptionPane;

public class WindowUtil {
	 // 将窗体大小设置成450×309
    public static Dimension getSize() {
        return new Dimension(450, 309);
    }
    
    // 计算窗体居中显示时左上角坐标
    public static Point getLocation() {
        Toolkit toolKit = Toolkit.getDefaultToolkit();// 获得Toolkit实例
        Dimension screenSize = toolKit.getScreenSize();// 获得显示器大小
        if ((screenSize.width < getSize().width) || (screenSize.height < getSize().height)) {
            JOptionPane.showMessageDialog(null, "显示器分辨率至少为450×309", "", JOptionPane.WARNING_MESSAGE);
            System.exit(0);// 终止程序
        }
        int x = (screenSize.width - getSize().width) / 2;// 计算左上角横坐标
        int y = (screenSize.height - getSize().height) / 2;// 计算左上角纵坐标
        return new Point(x, y);
    }
}
