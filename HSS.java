import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Component;
import java.awt.Event;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class HSS {
	JFrame frame1;
	JFrame frame2;
	JFrame frame3; //scheduler
	JFrame frame4;
	JFrame frame5;
	JFrame frame6;
	JFrame frame7;
	JFrame frame8;
	JFrame frame9;

	int check;
	private String friend_id = "";
	int i, j;

	int first_index;
	int last_index = 0;
	int num_schedule = 0;
	int day_num;

	String start_time = "";
	String finish_time = "";
	String notification_color;

	String userid = "";
	char[] password;
	String category = "";
	boolean invite = false;
	GuiFactory factory;
	String theme = "Theme1";
	AddSchedule addsch;
	
	Originator originator = new Originator();
	
	private static HSS hss = new HSS();

	private HSS() {
	}

	public static HSS getInstance() {
		return hss;
	}

	User user;

	public static void main(String[] args) {
		//HSS hss = new HSS();
		hss.init();
	}
	// login page를 띄워주는 method
	public void init() {
		frame1 = new JFrame("Login"); // login page
		final JLabel hss = new JLabel("Handong Student Scheduler"); // page 위쪽 문구
		hss.setBounds(50, 10, 200, 50); // page 크기

		JLabel id = new JLabel("ID:");
		id.setBounds(30, 80, 80, 30);
		JLabel pw = new JLabel("Password:");
		pw.setBounds(30, 120, 80, 30);

		final JTextField id_text = new JTextField(); // id 입력받는 textfield
		id_text.setBounds(100, 80, 100, 30);
		final JPasswordField pw_text = new JPasswordField(); // pw 입력받는 textfield
		pw_text.setBounds(100, 120, 100, 30);

		JButton bt_login = new JButton("Login"); // login button
		bt_login.setBounds(50, 180, 85, 30);
		JButton bt_register = new JButton("Register"); // register button
		bt_register.setBounds(140, 180, 85, 30);

		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // x 버튼 누르면 다 꺼지도록
		frame1.add(hss); // frame1에 각 component add
		frame1.add(id);
		frame1.add(pw);
		frame1.add(id_text);
		frame1.add(pw_text);
		frame1.add(bt_login);
		frame1.add(bt_register);
		frame1.setSize(300, 300); // frame size
		frame1.setLayout(null);
		frame1.setVisible(true); // frame이 보이도록

		bt_login.addActionListener(new ActionListener() { // login button 눌렀을 때 event 받는 listener 추가
			public void actionPerformed(ActionEvent e) { // event 발생하면
				System.out.println(id_text.getText());
				System.out.println(pw_text.getPassword());
				login(id_text.getText(), pw_text.getPassword());
				// String data = id_text.getText() + new String(pw_text.getPassword()); // text
				// field 값 받아서 저장

			}
		});
		bt_register.addActionListener(new ActionListener() { // register button 눌렀을 때 event 받는 listener 추가
			public void actionPerformed(ActionEvent e) {
				register(); // register 메소드
			}
		});

	}
	
	public void login(String id, char[] cs) {
		user = MemoryManager.getInstance().loginAuthentication(id, cs);

		if (user != null) {
			scheduler(); // scheduler 메소드(scheduler frame 띄워주는 메소드) invoke
			frame1.setVisible(false); // frame1 끔
		} else {
			JOptionPane.showMessageDialog(null, "로그인 오류.");
		}

	}
	
	public void notification() {
	      frame9 = new JFrame("Notification");

	      final JLabel label = new JLabel("Notification List");
	      label.setBounds(40, 30, 200, 15);
//	      final JLabel label2 = new JLabel("select Schedule");
//	      label2.setBounds(40, 60, 200, 15);
	      final JLabel label2 = new JLabel("Notification Info:");
	      label2.setBounds(320, 30, 200, 15);
	      final JTextArea label3 = new JTextArea();
	      label3.setBounds(320, 55, 200, 175);

	      final DefaultListModel<String> l1 = new DefaultListModel<>();
	      final DefaultListModel<String> l2 = new DefaultListModel<>();
	      
	      LinkedList<Notification> sc = user.getAllNotification();
	      Iterator<Notification> it = sc.iterator();
	      while (it.hasNext()) {

	         Notification s1 = it.next();
	         final String sender = s1.getSender();
	         Schedule schedule = s1.getSchedule();
	         int[] datetime = schedule.getDatetime();
	         String color = String.valueOf(schedule.getColor());
	         String n_name = schedule.getName();
	         String n_day = String.valueOf(datetime[0]);
	         String s_time = String.valueOf(datetime[1]);
	         String f_time = String.valueOf(datetime[2]);
	         

	         l1.addElement(sender + " " + n_name);
	         l2.addElement(n_name +" " + n_day + " " + s_time + " " + f_time + " " + color);
	      }
	      
	      final JList<String> list1 = new JList<>(l1);
	      final JList<String> list2 = new JList<>(l2);

	      list1.setBounds(40, 55, 200, 175);

	      JButton bt_accept = new JButton("Accept");
	      bt_accept.setBounds(180, 280, 85, 30);
	      JButton bt_delete = new JButton("Delete");
	      bt_delete.setBounds(280, 280, 85, 30);
	      
	      JButton bt_show = new JButton(">>");
	      bt_show.setBounds(250, 150, 60, 30);

	      frame9.add(label);
	      frame9.add(label2);
	      frame9.add(label3);
	      frame9.add(list1);
	      frame9.add(bt_accept);
	      frame9.add(bt_delete);
	      frame9.add(bt_show);
	      frame9.setSize(570, 400);
	      frame9.setLayout(null);
	      frame9.setVisible(true);

	      bt_accept.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            String info = l2.getElementAt(list1.getSelectedIndex());
	            //String info = list2.getComponent(list1.getSelectedIndex()).toString();
	            String s[] = info.split("\\s+");
	            String name = s[0];
	            String day = s[1];
	            String s_time = s[2];
	            String f_time = s[3]; 
	            String color = s[4];
	            if (color.equals("1")) {
	    			color = "class";

	    		} else if (color.equals("2")) {
	    			color = "ClubAndSociety";

	    		} else if (color.equals("3")) {
	    			color = "FixedMeal";

	    		} else if (color.equals("4")) {
	    			color = "ProjectMeeting";
	    		}
	            System.out.println("name, day, s_time, f_time " + color + " " + day + " " + s_time + " " + f_time);
	            addsch = new AddPrivateSchedule(user);
	            addsch.addScheduleStep1(color, null);
	            addsch.adScheduleStep2(Integer.valueOf(day), Integer.valueOf(s_time), Integer.valueOf(f_time), name);
	            //list1.remove(list1.getSelectedIndex());
	            user.getAllNotification().remove(list1.getSelectedIndex());
	            frame9.setVisible(false);
	            MemoryManager.getInstance().notificationProcedure(user);
	            frame3.setVisible(false);
	            scheduler();
	            notification();
	         }
	      });
	      bt_delete.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	            //list1.remove(list1.getSelectedIndex());
	            user.getAllNotification().remove(list1.getSelectedIndex());
	            frame9.setVisible(false);
	            MemoryManager.getInstance().notificationProcedure(user);
	            notification();
	         }
	      });
	      bt_show.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {
	             String data = "";  
	                 if (list1.getSelectedIndex() != -1) {                       
	                    data = l2.getElementAt(list1.getSelectedIndex());
	                    String s[] = data.split("\\s+");
//	                 String s_time = String.valueOf(datetime[1] / 2 + 8) + ":" + String.valueOf((datetime[1] % 2) * 30);
//	                 String f_time = String.valueOf((datetime[2] + 1) / 2 + 8) + ":"
//	                       + String.valueOf((datetime[2] + 1) % 2 == 0 ? "00" : "30");
	                String name = s[3];
	                String day = s[0];
	                String s_time = String.valueOf(Integer.valueOf(s[1]) / 2 + 8) + ":" + String.valueOf((Integer.valueOf(s[1]) % 2) * 30);
	                String f_time = String.valueOf((Integer.valueOf(s[2]) + 1) / 2 + 8) + ":"
	                       + String.valueOf((Integer.valueOf(s[2]) + 1) % 2 == 0 ? "00" : "30");   
	                data = "Schedule Name: " + name + "\nDay: " + day + "\nStart Time: " + s_time + "\nFinish Time: " + f_time;
	                    label3.setText(data);
	                 }  
	         }
	      });
	}

	public void register() { // register button 눌렀을 때 실행
		frame2 = new JFrame("Register");
		final JLabel register = new JLabel("Register");
		register.setBounds(30, 5, 100, 50);

		JLabel name = new JLabel("Name:");
		name.setBounds(30, 50, 80, 30);
		JLabel id = new JLabel("ID:");
		id.setBounds(30, 90, 80, 30);
		JLabel pw = new JLabel("Password:");
		pw.setBounds(30, 130, 80, 30);
		JLabel stu_num = new JLabel("Student #:");
		stu_num.setBounds(30, 170, 80, 30);

		final JTextField name_text = new JTextField(); // 입력받는 component
		name_text.setBounds(100, 50, 100, 30);
		final JTextField id_text = new JTextField();
		id_text.setBounds(100, 90, 100, 30);
		final JPasswordField pw_text = new JPasswordField();
		pw_text.setBounds(100, 130, 100, 30);
		final JTextField stu_num_text = new JTextField();
		stu_num_text.setBounds(100, 170, 100, 30);

		JButton bt_register = new JButton("Register"); // register button
		bt_register.setBounds(100, 215, 85, 30);

		// frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.add(register);
		frame2.add(name);
		frame2.add(id);
		frame2.add(pw);
		frame2.add(stu_num);
		frame2.add(name_text);
		frame2.add(id_text);
		frame2.add(pw_text);
		frame2.add(stu_num_text);
		frame2.add(bt_register);
		frame2.setSize(300, 300);
		frame2.setLayout(null);
		frame2.setVisible(true);

		bt_register.addActionListener(new ActionListener() { // register button 누르면 data에 저장하고 page 꺼짐
			public void actionPerformed(ActionEvent e) {
				// System.out.println(id_text.getText() + new String(pw_text.getPassword()) +
				// name_text.getText() + Integer.parseInt(stu_num_text.getText()));
				try {
					if (MemoryManager.getInstance().findUser(id_text.getText()) != null) {
						JOptionPane.showMessageDialog(null, "해당 아이다가 이미 존재합니다.");
					} else {
						MemoryManager.getInstance().registerUser(id_text.getText(), new String(pw_text.getPassword()),
								name_text.getText(), Integer.parseInt(stu_num_text.getText()));
						frame2.setVisible(false);
					}
				} catch (Exception exc) {
					JOptionPane.showMessageDialog(null, "모든 항목을 작성해 주세요.");
				}
			}
		});
	}

	public void scheduler() {
		frame3 = new JFrame("Login");

		final JLabel scheduler = new JLabel(user.getUserName() + "'s weekly Schedule");
		scheduler.setBounds(10, 20, 200, 10);
		JButton bt_add = new JButton("Add");
		bt_add.setBounds(450, 10, 85, 30);
		JButton bt_remove = new JButton("Remove");
		bt_remove.setBounds(545, 10, 85, 30);
		JButton bt_view = new JButton("view other's");
		bt_view.setBounds(640, 10, 130, 30);
		String select[] = { "Theme1", "Theme2" };
	    final JComboBox themeSelect = new JComboBox(select);
	    themeSelect.setBounds(40, 550, 100, 20);
	    JButton bt_noti = new JButton("Notification");
	    bt_noti.setBounds(640, 550, 130, 30);

		String column[] = { "시간", "월", "화", "수", "목", "금", "토", "일" };
		Object data[][] = { { "8:00" }, { "8:30" }, { "9:00" }, { "9:30" }, { "10:00" }, { "10:30" }, { "11:00" },
				{ "11:30" }, { "12:00" }, { "12:30" }, { "13:00" }, { "13:30" }, { "14:00" }, { "14:30" }, { "15:00" },
				{ "15:30" }, { "16:00" }, { "16:30" }, { "17:00" }, { "17:30" }, { "18:00" }, { "18:30" }, { "19:00" },
				{ "19:30" }, { "20:00" }, { "20:30" }, { "21:00" }, { "21:30" }, { "22:00" }, { "22:30" }, { "23:00" },
				{ "23:30" } };
		DefaultTableModel model = new DefaultTableModel(data, column);

		LinkedList<Schedule> sc = user.getAllSchedule();
		Iterator<Schedule> it = sc.iterator();
		while (it.hasNext()) {

			Schedule s1 = it.next();
			int[] datet = s1.getDatetime();
			for (int i = datet[1]; i <= datet[2]; i++) {
				model.setValueAt(s1.getName(), i, datet[0] + 1); // 이런식으로 time slot에 값 변강가능합니다
			}
		}

		JTable jt = new JTable(model);
		jt.setRowHeight(30);
		getClass();

		jt.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		jt.getColumnModel().getColumn(0).setPreferredWidth(30);
		jt.getColumn("월").setCellRenderer(new ButtonRenderer());
		jt.getColumn("월").setCellEditor(new ButtonEditor(new JCheckBox()));
		jt.getColumn("화").setCellRenderer(new ButtonRenderer());
		jt.getColumn("화").setCellEditor(new ButtonEditor(new JCheckBox()));
		jt.getColumn("수").setCellRenderer(new ButtonRenderer());
		jt.getColumn("수").setCellEditor(new ButtonEditor(new JCheckBox()));
		jt.getColumn("목").setCellRenderer(new ButtonRenderer());
		jt.getColumn("목").setCellEditor(new ButtonEditor(new JCheckBox()));
		jt.getColumn("금").setCellRenderer(new ButtonRenderer());
		jt.getColumn("금").setCellEditor(new ButtonEditor(new JCheckBox()));
		jt.getColumn("토").setCellRenderer(new ButtonRenderer());
		jt.getColumn("토").setCellEditor(new ButtonEditor(new JCheckBox()));
		jt.getColumn("일").setCellRenderer(new ButtonRenderer());
		jt.getColumn("일").setCellEditor(new ButtonEditor(new JCheckBox()));

		JScrollPane sp = new JScrollPane(jt);
		sp.setBounds(30, 65, 750, 450);

		frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame3.add(scheduler);
		frame3.add(bt_add);
		frame3.add(bt_remove);
		frame3.add(bt_view);
		frame3.add(sp);
		frame3.add(themeSelect);
		frame3.add(bt_noti);
		frame3.setSize(830, 650);
		frame3.setLayout(null);
		frame3.setVisible(true);
		frame3.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				MemoryManager.getInstance().exitProcedure(user);
			}
		});

		bt_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				check = 0;
				addSchedule1(-1, -1);
			}
		});
		bt_remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addSchedule5(null, -1, -1);
			}
		});
		bt_view.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		bt_noti.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            notification();
	         }
	    });
		themeSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				theme = (String) themeSelect.getItemAt(themeSelect.getSelectedIndex());
			}
		});
	}

	public void addSchedule1(int day, int time) {
		frame4 = new JFrame("Add Schedule");
	      
	      JPanel panel;
	      if(theme == "Theme1")
	         factory = new Factory1();
	      else if(theme == "Theme2")
	         factory = new Factory2();
	      
	      panel = factory.CreatePanel().makePanel();
	      panel.setSize(300, 300);
	      panel.setLayout(null);
	      
	      final JLabel label = new JLabel("Add Schedule");
	      label.setLocation(40, 20);
	      label.setSize(200, 10);
	      JCheckBox checkbox1 = new JCheckBox("초대하기");
	      checkbox1.setBackground(panel.getBackground());
	      checkbox1.setLocation(40, 80);
	      checkbox1.setSize(90, 30);
	      String select[] = { "수업", "학회/동아리", "밥고", "과제모임" };
	      final JComboBox cb = new JComboBox(select);
	      cb.setLocation(40, 60);
	      cb.setSize(90, 20);
	      
	      JButton bt_next = factory.CreateButton().makeButton("next");
	      bt_next.setLocation(100, 150);
	      bt_next.setSize(85, 30);
	      
	      panel.add(cb);
	      panel.add(label);
	      panel.add(checkbox1);
	      panel.add(bt_next);
	      frame4.setSize(300, 300);
	      frame4.add(panel);
	      frame4.setLayout(null);
	      frame4.setVisible(true);

		bt_next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switch ((String) cb.getItemAt(cb.getSelectedIndex())) {
				case "수업": {
					category = "class";
					break;
				}
				case "학회/동아리": {
					category = "ClubAndSociety";
					break;
				}
				case "밥고": {
					category = "FixedMeal";
					break;
				}
				case "과제모임": {
					category = "ProjectMeeting";
					break;
				}
				}

				invite = checkbox1.isSelected();
				//user.addSchedule(category, invite);
				if (invite) {
					addSchedule2(day, time);
					frame4.setVisible(false);
				} else {
					 int[][] available_timeslot = new int[32][7];
	                    addsch = new AddPrivateSchedule(user);
	                    // **************************
	                    available_timeslot = addsch.addScheduleStep1(category, null);
	                    addSchedule3(available_timeslot, day, time);
					frame4.setVisible(false);
				}
			}
		});
	}

	public void addSchedule2(int day, int time) {
		frame5 = new JFrame("Add Schedule");
		
		originator.clear();
		user.initializeInviteList();
		
		List<Originator.Memento> savedFriends = new ArrayList<Originator.Memento>();
		originator.setInvite("");
		savedFriends.add(originator.saveToMemento());
		
		JPanel panel;      
	    panel = factory.CreatePanel().makePanel();
	    panel.setSize(300, 300);
	    panel.setLayout(null);

	    final JLabel label = new JLabel("Invite Friends");
	    label.setLocation(40, 20);
	    label.setSize(200, 10);
	    final JLabel id = new JLabel("ID");
	    id.setLocation(40, 55);
	    id.setSize(200, 10);
	    JTextField tf_id = new JTextField();
	    tf_id.setLocation(70, 50);
	    tf_id.setSize( 100, 20);
	    final JLabel invite = new JLabel("Invite List");
	    invite.setLocation(40, 80);
	    invite.setSize(200, 10);
	    JTextArea area = new JTextArea();
	    area.setLocation(40, 100);
	    area.setSize(200, 50);

		JButton bt_search = new JButton("add");
		bt_search.setBounds(180, 50, 75, 20);
		JButton bt_back = factory.CreateButton().makeButton("back");
        bt_back.setBounds(60, 160, 80, 30);
        JButton bt_next = factory.CreateButton().makeButton("next");
        bt_next.setBounds(150, 160, 80, 30);

	    panel.add(label);
	    panel.add(bt_search);
	    panel.add(id);
	    panel.add(tf_id);
	    panel.add(invite);
	    panel.add(area);
	    panel.add(bt_next);
	    panel.add(bt_back);
	    frame5.add(panel);
	    frame5.setSize(300, 300);
	    frame5.setLayout(null);
	    frame5.setVisible(true);
		frame5.addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				friend_id = "";
				area.setText(null);
			}
		});

		bt_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (user.requestSearchUser(tf_id.getText())) {
					originator.setInvite(tf_id.getText() + "\n");
					savedFriends.add(originator.saveToMemento());
					area.setText(savedFriends.get(savedFriends.size() - 1).getSavedTime());
					tf_id.setText("");
				} else {
					tf_id.setText("");
					JOptionPane.showMessageDialog(null, "해당 아이디는 존재하지 않습니다.");
				}
				System.out.println(friend_id);
			}
		});
		bt_next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame5.setVisible(false);
				int[][] available_timeslot = new int[32][7];
				addsch = new AddGroupSchedule(user);
				available_timeslot = addsch.addScheduleStep1(category, user.getInviteList());
				addSchedule3(available_timeslot, day, time);
				friend_id = "";
				area.setText(null);
			}
		});

		bt_back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (savedFriends.size() > 2) {
					user.removeLastFriend();
					originator.restoreFromMemento(savedFriends.get(savedFriends.size() - 2));
					savedFriends.remove(savedFriends.size() - 1);
					area.setText(savedFriends.get(savedFriends.size() - 1).getSavedTime());
					System.out.println(savedFriends.size());
				} else if (savedFriends.size() == 2) {
					savedFriends.remove(savedFriends.size() - 1);
					originator.restoreFromMemento(savedFriends.get(0));
					user.removeLastFriend();
					area.setText("");
					System.out.println(savedFriends.size());
				}
			}
		});
	}

	public void addSchedule3(int[][] timeslot, int day, int time) {
		frame6 = new JFrame("Add Schedule");
	      
	    JPanel panel;
	    panel = factory.CreatePanel().makePanel();
	    panel.setSize(400, 850);
	    panel.setLayout(null);

	    final JLabel label = new JLabel("Select time");
	    label.setLocation(30, 20);
	    label.setSize(200, 15);

	    final JLabel label2 = new JLabel("월");
	    label2.setLocation(95, 50);
	    label2.setSize(200, 15);
	    final JLabel label3 = new JLabel("화");
	    label3.setLocation(115, 50);
	    label3.setSize(200, 15);
	    final JLabel label4 = new JLabel("수");
	    label4.setLocation(135, 50);
	    label4.setSize(200, 15);
	    final JLabel label5 = new JLabel("목");
	    label5.setLocation(155, 50);
	    label5.setSize(200, 15);
	    final JLabel label6 = new JLabel("금");
	    label6.setLocation(175, 50);
	    label6.setSize(200, 15);
	    final JLabel label7 = new JLabel("토");
	    label7.setLocation(195, 50);
	    label7.setSize(200, 15);
	    final JLabel label8 = new JLabel("일");
	    label8.setLocation(215, 50);
	    label8.setSize(200, 15);

		JLabel[] labels = new JLabel[16];
		for (i = 0; i < 16; i++) {
			labels[i] = new JLabel(String.valueOf(i + 8) + ":00");
			labels[i].setLocation(30, 70 + i * 40);
            labels[i].setSize(50, 15);
			frame6.add(labels[i]);
		}
		JLabel[] labels2 = new JLabel[16];
		for (i = 0; i < 16; i++) {
			labels2[i] = new JLabel(String.valueOf(i + 8) + ":30");
			labels2[i].setLocation(30, 90 + i * 40);
            labels2[i].setSize(50, 15);
			frame6.add(labels2[i]);
		}
		
		JButton[][] buttons = new JButton[32][7];
		for (i = 0; i < 32; i++) {
			for (j = 0; j < 7; j++) {
				buttons[i][j] = new JButton();
				buttons[i][j].setName(String.valueOf(i) + " " + String.valueOf(j));
				// buttons[i][j].setActionCommand("button" +i +"_" +j);
				if ((j == day)&&(i == time)) {
					buttons[i][j].setBackground(Color.yellow);
				}
				if (timeslot[i][j] == 1) {
					buttons[i][j].setBackground(Color.black);
					buttons[i][j].setEnabled(false);
				}

				buttons[i][j].addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JButton pressedButton = (JButton) e.getSource();
						if(pressedButton.getBackground().equals(new JButton().getBackground()))
							pressedButton.setBackground(Color.yellow);
						else if(pressedButton.getBackground().equals(Color.yellow))
							pressedButton.setBackground(new JButton().getBackground());
						/*String data = pressedButton.getName();
						String s[] = data.split("\\s+");
						int last = Integer.parseInt(s[0]);
						if (last > last_index)
							last_index = last;
						day_num = Integer.parseInt(s[1]);
						num_schedule += 1;*/
					}

				});
				buttons[i][j].setLocation(90 + j * 20, 70 + i * 20);
                buttons[i][j].setSize(20, 20);
				frame6.add(buttons[i][j]);
			}
		}

		JButton bt_next = factory.CreateButton().makeButton("next");
        bt_next.setLocation(100, 750);
        bt_next.setSize(85, 30);
		bt_next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (i = 0; i < 32; i++) {
					for (j = 0; j < 7; j++) {
						if(buttons[i][j].getBackground().equals(Color.yellow)){
							String data = buttons[i][j].getName();
							String s[] = data.split("\\s+");
							int last = Integer.parseInt(s[0]);
							if (last > last_index)
								last_index = last;
							day_num = Integer.parseInt(s[1]);
							num_schedule += 1;
						}
					}
				}
				// addSchedule4();
				first_index = last_index - num_schedule + 1;
				// System.out.println("first:"+first_index + " last:"+last_index + "
				// day_index:"+ day_num);
				start_time = String.valueOf(first_index / 2 + 8) + ":" + String.valueOf((first_index % 2) * 30);
				// System.out.println(start_time);
				finish_time = String.valueOf((last_index + 1) / 2 + 8) + ":"
						+ String.valueOf((last_index + 1) % 2 == 0 ? "00" : "30");
				// System.out.println(finish_time);

				addSchedule4();
				frame6.setVisible(false);
			}
		});

		panel.add(label);
        panel.add(label2);
        panel.add(label3);
        panel.add(label4);
        panel.add(label5);
        panel.add(label6);
        panel.add(label7);
        panel.add(label8);
        panel.add(bt_next);
		frame6.add(panel);
		frame6.setSize(400, 850);
		frame6.setLayout(null);
		frame6.setVisible(true);

	}

	public void addSchedule4() {
		frame7 = new JFrame("Add Schedule");
		String day = null;
		
		JPanel panel;      
	    panel = factory.CreatePanel().makePanel();
	    panel.setSize(300, 300);
	    panel.setLayout(null);


		switch (day_num) {
		case 0:
			day = "월";
			break;
		case 1:
			day = "화";
			break;
		case 2:
			day = "수";
			break;
		case 3:
			day = "목";
			break;
		case 4:
			day = "금";
			break;
		case 5:
			day = "토";
			break;
		case 6:
			day = "일";
			break;
		}

		final JLabel label = new JLabel("Detailed settings");
	    label.setLocation(40, 20);
	    label.setSize(200, 15);
	    final JLabel label2 = new JLabel("요일: " + day);
	    label2.setLocation(40, 50);
	    label2.setSize(200, 15);
	    final JLabel label3 = new JLabel("시간: " + start_time + " - " + finish_time);
	    label3.setLocation(40, 70);
	    label3.setSize(200, 15);
	    final JLabel label4 = new JLabel("모임이름");
	    label4.setLocation(40, 90);
	    label4.setSize(200, 15);
	    JTextField tf_name = new JTextField();
	    tf_name.setLocation(100, 90);
	    tf_name.setSize(100, 20);
//	    final JLabel label5 = new JLabel("장소");
//	    label5.setLocation(40, 110);
//	    label5.setSize(200, 15);
//	    JTextField tf_place = new JTextField();
//	    tf_place.setLocation(100, 110);
//	    tf_place.setSize(100, 20);

	    JButton bt_next = factory.CreateButton().makeButton("Create");
		bt_next.setBounds(100, 150, 85, 30);

		panel.add(label);
	    panel.add(label2);
	    panel.add(label3);
	    panel.add(label4);
	    panel.add(tf_name);
	    //frame7.add(label5);
	    //frame7.add(tf_place);
	    panel.add(bt_next);
	    frame7.add(panel);
	    frame7.setSize(300, 300);
	    frame7.setLayout(null);
	    frame7.setVisible(true);


		bt_next.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				frame7.setVisible(false);
				String name = tf_name.getText();
				// String place = tf_place.getText();
				
				if(!user.getInviteList().isEmpty()) {
					AddScheduleLastPage aslpd = new KindAddGroupScheduleDecorator(new RealAddScheduleLastPage());
					aslpd.setValues(addsch, user, category,day_num, first_index, last_index, name);
					
				}else {
					AddScheduleLastPage aslpd = new KindAddPrivateScheduleDecorator(new RealAddScheduleLastPage());
					aslpd.setValues(addsch, user, category,day_num, first_index, last_index, name);
					
				}	
				
				num_schedule = 0;
				last_index = 0;

				frame3.setVisible(false);
				scheduler();
			}
		});

	}

	public void addSchedule5(String sched, int date, int time) {
		frame8 = new JFrame("Delete Schedule");
		//String day = null;
		int temp = -1;

		final JLabel label = new JLabel("Delete Schedule");
		label.setBounds(40, 20, 200, 15);
		final JLabel label2 = new JLabel("select Schedule");
		label2.setBounds(40, 60, 200, 15);

		final DefaultListModel<String> l1 = new DefaultListModel<>();
		// DefaultListModel<Schedule> sc1 = new DefaultListModel<>();

		// remove iteration 
		LinkedList<Schedule> sc = user.getAllSchedule();
		Iterator<Schedule> it = sc.iterator();
		
		while (it.hasNext()) {

			Schedule s1 = it.next();
			int[] datet = s1.getDatetime();
			String name = s1.getName();
			if (datet[0] == date) {
                if ((datet[1] <= time) && (datet[2] >= time))
                    if (name.equals(sched))
                        temp = l1.getSize();
			}
            String day = changeDay(datet[0]); // changeDay(int day)
			String s_time = String.valueOf(datet[1] / 2 + 8) + ":" + String.valueOf((datet[1] % 2) * 30);
			String f_time = String.valueOf((datet[2] + 1) / 2 + 8) + ":"
					+ String.valueOf((datet[2] + 1) % 2 == 0 ? "00" : "30");
			l1.addElement(name + " " + day + " " + s_time + " " + f_time);
			// l1.add(0, name + " " + s_time + " " + f_time);
			// sc1.addElement(s1);
		}
		
		final JList<String> list1 = new JList<>(l1);
		// final JList<Schedule> list2 = new JList<>(sc1);

		//list1.setBounds(40, 75, 200, 175);
		JScrollPane sp = new JScrollPane(list1);
		sp.setBounds(40, 75, 200, 175);

		JButton bt_delete = new JButton("Delete");
		bt_delete.setBounds(100, 260, 85, 30);
		if(temp != -1)
			list1.setSelectedIndex(temp);

		frame8.add(label);
		frame8.add(label2);
		frame8.add(sp);
		frame8.add(bt_delete);
		frame8.setSize(300, 500);
		frame8.setLayout(null);
		frame8.setVisible(true);

		bt_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame8.setVisible(false);
				int[] datet = new int[3];
				/*
				 * Schedule schedule = list2.getSelectedValue(); user.removeSchedule(schedule);
				 */

				String schedule = list1.getSelectedValue();
				String s[] = schedule.split("\\s+");
				String name = s[0];
				 
                // ******************새나 수정 date time 을 schedule 찾는 조건으로 추가함. ************//
                datet[0] = changeDay(s[1]);
                String t1[] = s[2].split(":");
                String t2[] = s[3].split(":");
 
                System.out.println("split degug s:" + s[3]);
                System.out.println("split degug t" + t2[0]);
 
                datet[1] = (Integer.parseInt(t1[0]) * 2) % 16 + (t1[1].equals("30") ? 1 : 0)
                        + (((Integer.parseInt(t1[0]) * 2) / 16) == 2 ? 16 : 0);
                datet[2] = (Integer.parseInt(t2[0]) * 2) % 16 + (t2[1].equals("30") ? 0 : -1)
                        + (((Integer.parseInt(t2[0]) * 2) / 16) == 2 ? 16 : 0);
 
                System.out.println("f_date degug" + String.valueOf(datet[2]));
 
                System.out.println("date time:  " + String.valueOf(datet[0]) + String.valueOf(datet[1])
                        + String.valueOf(datet[2]));
                RemoveSchedule rs = new RemoveSchedule(user);
                rs.removeSchedule(name, datet);

				frame3.setVisible(false);
				scheduler();
			}
		});
	}
	
	public String changeDay(int day) {
		 
        switch (day) {
 
        case 0:
            return "Mon";
        case 1:
            return "Tue";
        case 2:
            return "Wed";
        case 3:
            return "Thu";
        case 4:
            return "Fri";
        case 5:
            return "Sat";
        case 6:
            return "Sun";
 
        }
 
        return null;
 
    }
 
    public int changeDay(String day) {
 
        switch (day) {
 
        case "Mon":
            return 0;
        case "Tue":
            return 1;
        case "Wed":
            return 2;
        case "Thu":
            return 3;
        case "Fri":
            return 4;
        case "Sat":
            return 5;
        case "Sun":
            return 6;
 
        }
 
        return -1;
    }
}

class ButtonRenderer extends JButton implements TableCellRenderer {

	public ButtonRenderer() {
		setOpaque(true);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		if (isSelected) {
			setForeground(table.getSelectionForeground());
			setBackground(table.getSelectionBackground());
		} else {
			setForeground(table.getForeground());
			setBackground(UIManager.getColor("Button.background"));
		}
		setText((value == null) ? "" : value.toString());
		return this;
	}
}

class ButtonEditor extends DefaultCellEditor {
	protected JButton button;
	private String label;
	private boolean isPushed;
	private int day, time;

	public ButtonEditor(JCheckBox checkBox) {
		super(checkBox);
		button = new JButton();
		button.setOpaque(true);
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				fireEditingStopped();
				// button.setBackground(Color.YELLOW);
			}
		});
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		if (isSelected) {
			button.setForeground(table.getSelectionForeground());
			button.setBackground(table.getSelectionBackground());
		} else {
			button.setForeground(table.getForeground());
			button.setBackground(table.getBackground());
		}
		day = column - 1;
		time = row;
		label = (value == null) ? "" : value.toString();
		button.setText(label);
		isPushed = true;
		return button;
	}

	@Override
	public Object getCellEditorValue() {
		if (isPushed && (label != "")) {
			//JOptionPane.showMessageDialog(button, label);
			HSS.getInstance().addSchedule5(label, day, time);
		}
		//state design pattern???
		else if(isPushed && (label == "")) {
			//JOptionPane.showMessageDialog(button, "Empty");
			HSS.getInstance().addSchedule1(day, time);
		}
		isPushed = false;
		return label;
	}

	@Override
	public boolean stopCellEditing() {
		isPushed = false;
		return super.stopCellEditing();
	}
}