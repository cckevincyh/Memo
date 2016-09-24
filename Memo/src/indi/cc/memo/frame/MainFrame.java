package indi.cc.memo.frame;

import indi.cc.memo.util.WindowUtil;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

//备忘录主窗体
public class MainFrame extends JFrame{
	private JMenuBar menuBar;	//应用菜单条
	private JMenu memoManagementMenu;	//备忘录管理菜单
	private JMenu toolMenu;	//小工具菜单
	private JMenuItem calculatorMemoMenuItem;	//计算器菜单项
	private JMenuItem notepadMenuItem;	//记事本菜单项
	private JMenuItem addMemoMenuItem;	//增加备忘录菜单项
	private JMenuItem modifyMemoMemuItem;	//修改备忘录菜单项
	private JMenuItem queryMemoMenuItem;	//查询备忘录菜单项
	private JMenuItem deleteMemoMenuItem;	//删除备忘录菜单项
	private JPanel contentPane;	//面板
	private JFrame jf;	//当前窗体
	public MainFrame(){
		this.jf = this;
		this.setTitle("备忘录模块");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuBar = new JMenuBar();	//创建应用菜单条
		this.setJMenuBar(menuBar);	//添加应用菜单条
		memoManagementMenu = new JMenu("备忘录管理");	//创建备忘录管理菜单
		menuBar.add(memoManagementMenu);	//在菜单条中添加备忘录管理菜单
		
		addMemoMenuItem = new JMenuItem("增加备忘录");//创建增加备忘录菜单项
		//注册事件监听
		addMemoMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MemoAddtionFrame addtionFrame = new MemoAddtionFrame(jf, "增加备忘录", true);
			}
		});
		memoManagementMenu.add(addMemoMenuItem);	//在备忘录管理菜单中添加增加备忘录菜单项
		
		modifyMemoMemuItem = new JMenuItem("修改备忘录");//创建修改备忘录菜单项
		//注册事件监听
		modifyMemoMemuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MemoModificationFrame memoModificationFrame = new MemoModificationFrame(jf, "修改备忘录", true);
				
			}
		});
		memoManagementMenu.add(modifyMemoMemuItem);	//在备忘录管理菜单中添加修改备忘录菜单项
		
		queryMemoMenuItem = new JMenuItem("查询备忘录");	//创建查询备忘录菜单项
		//注册事件监听
		queryMemoMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MemoQueryFrame memoQueryFrame = new MemoQueryFrame(jf, "查询备忘录", true);
				
			}
		});
		memoManagementMenu.add(queryMemoMenuItem);	//在备忘录管理菜单中添加查询备忘录菜单项
		
		deleteMemoMenuItem = new JMenuItem("删除备忘录");//创建删除备忘录菜单项
		//注册事件监听
		deleteMemoMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MemoDeletionFrame memodeletionFrame = new MemoDeletionFrame(jf, "删除备忘录", true);
				
			}
		});
		memoManagementMenu.add(deleteMemoMenuItem);	//在备忘录管理菜单中添加删除备忘录菜单项
		
		toolMenu = new JMenu("小工具");	//创建小工具菜单
		menuBar.add(toolMenu);	//在菜单条中添加小工具菜单
		
		notepadMenuItem = new JMenuItem("记事本");	//创建记事本菜单项
		//"记事本"菜单项事件监听
		notepadMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Runtime runtime = Runtime.getRuntime();// 获得Runtime类型对象
				try {
					runtime.exec("notepad");// 执行notepad命令打开记事本
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		toolMenu.add(notepadMenuItem);	//在小工具菜单中添加记事本菜单项
		
		calculatorMemoMenuItem = new JMenuItem("计算器");	//创建计算器菜单项
		//"计算器"菜单项事件监听
		calculatorMemoMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Runtime runtime = Runtime.getRuntime();// 获得Runtime类型对象
				try {
					runtime.exec("calc");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	//执行打开计算器命令
				
			}
		});
		toolMenu.add(calculatorMemoMenuItem);	//在小工具菜单中添加计算器菜单项
		
		contentPane = new JPanel(){
			public void paint(Graphics g) {
				 super.paint(g);// 调用父类构造方法
	                Graphics2D g2 = (Graphics2D) g;// 获得Graphics2D对象
	                String str = "备忘录模块";// 设置要显示的字符串
	                Font font = new Font("隶书", Font.BOLD | Font.ITALIC, 40);// 创建新字体
	                g2.setFont(font);// 应用字体
	                for (int i = 0; i < str.length(); i++) {
	                    g2.setColor(Color.GRAY); // 设置前景色
	                    g2.drawString(str.charAt(i) + "", 50 + i * font.getSize(), 50 + i * font.getSize()); // 在指定位置绘制文本
	                    g2.drawString(str.charAt(i) + "", 370 - i * font.getSize(), 50 + i * font.getSize()); // 在指定位置绘制文本
	                }
	                for (int i = 0; i < str.length(); i++) {
	                    g2.setColor(Color.BLUE); // 设置前景色
	                    g2.drawString(str.charAt(i) + "", 40 + i * font.getSize(), 40 + i * font.getSize()); // 在指定位置绘制文本
	                    g2.drawString(str.charAt(i) + "", 360 - i * font.getSize(), 40 + i * font.getSize()); // 在指定位置绘制文本
	                }
	        	}
		};
		contentPane.setBackground(Color.CYAN);
	        setContentPane(contentPane);// 应用面板
	        
	        setLocation(WindowUtil.getLocation());// 设置窗体显示位置
	        setSize(WindowUtil.getSize());// 设置窗体大小
	        this.setResizable(false);
	        this.setVisible(true);
	}
}
