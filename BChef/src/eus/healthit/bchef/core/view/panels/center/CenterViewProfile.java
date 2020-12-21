package eus.healthit.bchef.core.view.panels.center;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.JTextComponent;

import eus.healthit.bchef.core.models.User;

public class CenterViewProfile extends JPanel {

	User user;

	public CenterViewProfile(User user) {
		super(new GridLayout(1, 1, 100, 100));
		this.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
		this.setBackground(Color.white);
		this.setOpaque(true);

		this.user = user;

		this.add(createContent());
	}

	private JPanel createContent() {
		JPanel contentPanel = new JPanel(new BorderLayout(20, 20));
		contentPanel.setBackground(Color.white);
		contentPanel.setOpaque(true);

		contentPanel.add(createNorthPanel(), BorderLayout.NORTH);
		contentPanel.add(createCenterPanel(), BorderLayout.CENTER);

		return contentPanel;
	}

	private JPanel createNorthPanel() {
		JPanel northPanel = new JPanel(new BorderLayout(20, 20));
		northPanel.setBackground(Color.white);
		northPanel.setOpaque(true);

		JPanel imagePanel = new JPanel(new GridLayout(1, 1, 10, 10));
		JPanel infoPanel = new JPanel(new GridLayout(2, 1, 5, 5));
		
		imagePanel.setBackground(Color.white);
		imagePanel.setOpaque(true);
		
		infoPanel.setBackground(Color.white);
		infoPanel.setOpaque(true);

		// -------------------------------------------------------------
		JLabel profilePicture = new JLabel();
		profilePicture.setIcon(user.getProfilePic());
		imagePanel.add(profilePicture);
		// -------------------------------------------------------------

		JLabel username = new JLabel(user.getUsername());
		infoPanel.add(username);

		JPanel followerPanel = new JPanel(new GridLayout(1, 3, 10, 10));
		followerPanel.setBackground(Color.white);
		followerPanel.setOpaque(true);

		JLabel recipes = new JLabel("Recipes\n" + user.getPublishedNumber());
		JLabel following = new JLabel("Following\n" + user.getFollowedNumber());
		JLabel followers = new JLabel("Followers\n " + "Todavia no se si meter followers");

		followerPanel.add(recipes);
		followerPanel.add(following);
		followerPanel.add(followers);

		infoPanel.add(followerPanel);

		northPanel.add(imagePanel, BorderLayout.WEST);
		northPanel.add(infoPanel, BorderLayout.CENTER);

		return northPanel;
	}

	private JPanel createCenterPanel() {
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(Color.orange);

		return centerPanel;
	}

	public JPanel getPanel() {
		return this;
	}
}
