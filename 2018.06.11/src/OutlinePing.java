import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.border.BevelBorder;

public class OutlinePing extends JFrame {

	public OutlinePing(){
		//menu begin
		JMenuBar menubar = new JMenuBar();
		setJMenuBar(menubar);
		
		JMenu scanMenu = new JMenu("File");
		JMenu gotoMenu = new JMenu("Go to");
		JMenu commandsMenu = new JMenu("Commands");
		JMenu favoriteMenu = new JMenu("Favorites");
		JMenu toolsMenu = new JMenu("Tools");
		JMenu helpMenu = new JMenu("Help");
		
		menubar.add(scanMenu);
		menubar.add(gotoMenu);
		menubar.add(commandsMenu);
		menubar.add(favoriteMenu);
		menubar.add(toolsMenu);
		menubar.add(helpMenu);
		
		JMenuItem loadFromFilesAction = new JMenuItem("Load from file...");
		JMenuItem exportAllAction = new JMenuItem("Export All...");
		JMenuItem exportSelectionAction = new JMenuItem("Export selection...");
		JMenuItem quitAction = new JMenuItem("Quit");
		
		scanMenu.add(loadFromFilesAction);
		scanMenu.add(exportAllAction);
		scanMenu.add(exportSelectionAction);
		scanMenu.addSeparator();
		scanMenu.add(quitAction);
		
		JMenuItem nextAliveHostAction = new JMenuItem("Next alive host");
		JMenuItem nextOpenPortAction = new JMenuItem("Next open port");
		JMenuItem nextDeadHostAction = new JMenuItem("Next dead host");
		JMenuItem previousAliveHostAction = new JMenuItem("Previous alive host");
		JMenuItem previousOpenPortAction = new JMenuItem("Previous open port");
		JMenuItem previousDeadHostAction = new JMenuItem("Previous dead host");
		JMenuItem findAction = new JMenuItem("Find...");
		
		gotoMenu.add(nextAliveHostAction);
		gotoMenu.add(nextOpenPortAction);
		gotoMenu.add(nextDeadHostAction);
		gotoMenu.addSeparator();
		gotoMenu.add(previousAliveHostAction);
		gotoMenu.add(previousOpenPortAction);
		gotoMenu.add(previousDeadHostAction);
		gotoMenu.addSeparator();
		gotoMenu.add(findAction);
		
		JMenuItem showDetailsAction = new JMenuItem("Show details");
		JMenuItem rescanIPsAction = new JMenuItem("Rescan IP(S)");
		JMenuItem deleteIPsAction = new JMenuItem("Delete IP(S)");
		JMenuItem copyIPAction = new JMenuItem("Copy IP");
		JMenuItem copyDetailsAction = new JMenuItem("Copy details");
		JMenuItem openAction = new JMenuItem("Open");
		
		commandsMenu.add(showDetailsAction);
		commandsMenu.addSeparator();
		commandsMenu.add(rescanIPsAction);
		commandsMenu.add(deleteIPsAction);
		commandsMenu.addSeparator();
		commandsMenu.add(copyIPAction);
		commandsMenu.add(copyDetailsAction);
		commandsMenu.addSeparator();
		commandsMenu.add(openAction);
		
		JMenuItem addCurrentAction = new JMenuItem("Add current...");
		JMenuItem manageFavoritesAction = new JMenuItem("Manage favorites...");
		
		favoriteMenu.add(addCurrentAction);
		favoriteMenu.add(manageFavoritesAction);
		favoriteMenu.addSeparator();
		
		JMenuItem preferencesAction = new JMenuItem("Preferences...");
		JMenuItem fetchersAction = new JMenuItem("Fetchers...");
		JMenuItem selectionAction = new JMenuItem("Selection");
		JMenuItem scanStatisticsAction = new JMenuItem("Scan statistics");
		
		toolsMenu.add(preferencesAction);
		toolsMenu.add(fetchersAction);
		toolsMenu.addSeparator();
		toolsMenu.add(selectionAction);
		toolsMenu.add(scanStatisticsAction);
		
		JMenuItem gettingStartedAction = new JMenuItem("Getting Started");
		JMenuItem officialWebsiteAction = new JMenuItem("Official Website");
		JMenuItem faqAction = new JMenuItem("FAQ");
		JMenuItem reportAnIssueAction = new JMenuItem("Report an issue");
		JMenuItem pluginsAction = new JMenuItem("Plugins");
		JMenuItem commandLineUsageAction = new JMenuItem("Command-line usage");
		JMenuItem checkForNewerVersionAction = new JMenuItem("Check for newer version");
		JMenuItem aboutAction = new JMenuItem("About");
		
		helpMenu.add(gettingStartedAction);
		helpMenu.addSeparator();
		helpMenu.add(officialWebsiteAction);
		helpMenu.add(faqAction);
		helpMenu.add(reportAnIssueAction);
		helpMenu.add(pluginsAction);
		helpMenu.addSeparator();
		helpMenu.add(commandLineUsageAction);
		helpMenu.addSeparator();
		helpMenu.add(checkForNewerVersionAction);
		helpMenu.addSeparator();
		helpMenu.add(aboutAction);
		
		quitAction.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);	
			}
		});
		
		//menu end
		
		//status bar begim
		
		JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		statusPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
		add(statusPanel, BorderLayout.SOUTH);
		JLabel readyLabel = new JLabel("Ready");
		readyLabel.setPreferredSize(new Dimension(200,16));
		readyLabel.setBorder(new BevelBorder(BevelBorder.RAISED));
		JLabel displayLabel = new JLabel("Display:All");
		displayLabel.setPreferredSize(new Dimension(140,16));
		displayLabel.setBorder(new BevelBorder(BevelBorder.RAISED));
		JLabel threadLabel = new JLabel("Thread:0");
		threadLabel.setPreferredSize(new Dimension(140,16));
		threadLabel.setBorder(new BevelBorder(BevelBorder.RAISED));
		statusPanel.add(readyLabel);
		statusPanel.add(displayLabel);
		statusPanel.add(threadLabel);
		
		
		//status bar end
		
		//table begin
		String[] titles = new String[] {
			"°ËIP", "Ping", "TTL", "Hostname", "Ports [4+]"	
		};
		Object[][] stats = initTable();
		JTable jTable = new JTable(stats,titles);
		
		JScrollPane sp = new JScrollPane(jTable);
		add(sp, BorderLayout.CENTER);
		//table end 
		
		//toolbar begin
		Font myFont = new Font("Serif", Font.BOLD, 16);
		JToolBar toolbar1 = new JToolBar();
		toolbar1.setLayout(new FlowLayout(FlowLayout.LEFT));
		JToolBar toolbar2 = new JToolBar();
		toolbar2.setLayout(new FlowLayout(FlowLayout.LEFT));
		
		JLabel rangeStartLabel = new JLabel("IP Range: ");
		JTextField rangeStartTextField = new JTextField(10);
		JLabel rangeEndLabel = new JLabel("to");
		JTextField rangeEndTextField = new JTextField(10);
		
		rangeStartLabel.setFont(myFont);
		rangeStartLabel.setPreferredSize(new Dimension(72,30));
		rangeEndLabel.setFont(myFont);
		rangeEndLabel.setPreferredSize(new Dimension(15,30));

		
		toolbar1.add(rangeStartLabel);
		toolbar1.add(rangeStartTextField);
		toolbar1.add(rangeEndLabel);
		toolbar1.add(rangeEndTextField);
		
		JLabel hostNameLabel = new JLabel("Hostname: ");
		JTextField hostNameTextField = new JTextField(10);
		JButton upButton = new JButton("IP");
		JComboBox optionComboBox = new JComboBox();
		optionComboBox.addItem("/24");
		optionComboBox.addItem("/26");
		JButton startButton = new JButton("Start");
		
		hostNameLabel.setFont(myFont);
		hostNameTextField.setPreferredSize(new Dimension(90, 30));
		upButton.setPreferredSize(new Dimension(50, 30));
		optionComboBox.setPreferredSize(new Dimension(90,30));
		startButton.setPreferredSize(new Dimension(60,30));
		
		toolbar2.add(hostNameLabel);
		toolbar2.add(hostNameTextField);
		toolbar2.add(upButton);
		toolbar2.add(optionComboBox);
		toolbar2.add(startButton);
		
		JPanel pane = new JPanel(new BorderLayout());
		pane.add(toolbar1, BorderLayout.NORTH);
		pane.add(toolbar2, BorderLayout.SOUTH);
		
		add(pane, BorderLayout.NORTH);
		//toolbar end
		
		String myIp = null;
		String myHostname = null;
		try{
			InetAddress ia = InetAddress.getLocalHost();
			
			myIp = ia.getHostAddress();
			
			myIp = ia.getHostAddress();
			myHostname = ia.getHostName();
		} catch(Exception e) {
			
		}
		String fixedIp = myIp.substring(0, myIp.lastIndexOf(".") + 1);
		rangeStartTextField.setText(fixedIp + "1");
		rangeEndTextField.setText(fixedIp + "254");
		hostNameTextField.setText(myHostname);
		
		setSize(700,700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Pinging[] pg = new Pinging[254];
				
				for(int i=0; i<=253; i++){
					pg[i] = new Pinging(fixedIp+ (i+1));
					pg[i].start();
				}
				for(int i=0; i<=253; i++) {
					Object[] msg = pg[i].getMsg();
					stats[i][0] = msg[0];
					if(msg[1] != null) {
						stats[i][1] = msg[1];
					} else {
						stats[i][1] = "[n/a]";
					}
					if(msg[2] != null) {
						stats[i][2] = msg[2];
					} else {
						stats[i][2] = "[n/s]";
					}
					if(msg[3] != null) {
						stats[i][3] = msg[3];
					} else {
						stats[i][3] = "[n/a]";
					}
				}
					//msg[1] != null || msg[2] !=null || msg[3] != null
					//portscan.
					//scan value == null => stats[i][4] = "[n/s]"
					//scan value != null => assign value stats[i][4]
				
				jTable.repaint();
			}
		});
	}
	
	public Object[][] initTable() {
		
		Object[][] result = new Object[254][5];
		return result;
	}
	public static void main(String[] args) {
		OutlinePing op = new OutlinePing();
	}
}