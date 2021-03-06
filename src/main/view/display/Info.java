/*
 *  Brick Destroy - A simple Arcade video game
 *   Copyright (C) 2017  Filippo Ranza
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package main.view.display;

import main.controller.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;


/**
 * this is the info class where will show what the info page look like
 */

public class Info extends JComponent implements MouseListener, MouseMotionListener {

    private static final String GREETINGS = "";
    private static final String GAME_TITLE = "";
    private static final String CREDITS = "";
    private static final String BACK_TEXT = "Back";
    private static final String MENU_TEXT = "Exit";

    private static final Color BG_COLOR = new Color(134,51,255);//( purple )background color
    private static final Color BORDER_COLOR = new Color(200, 8, 21); //Venetian Red
    private static final Color DASH_BORDER_COLOR = new Color(255, 216, 0);//school bus yellow
    private static final Color TEXT_COLOR = new Color(254, 245, 231);//yellowish white
    private static final Color CLICKED_BUTTON_COLOR = BG_COLOR.brighter();
    private static final Color CLICKED_TEXT = Color.WHITE;
    private static final int BORDER_SIZE = 5;
    private static final float[] DASHES = {12, 6};

    private Rectangle menuFace;
    private Rectangle backButton;
    private Rectangle menuButton;


    private BasicStroke borderStoke;
    private BasicStroke borderStoke_noDashes;

    private Font greetingsFont;
    private Font gameTitleFont;
    private Font creditsFont;
    private Font buttonFont;

    private GameFrame owner;

    private boolean backClicked;
    private boolean menuClicked;

    /**
     *
     * @param owner
     * @param area
     */

    public Info(GameFrame owner, Dimension area) {

        this.setFocusable(true);
        this.requestFocusInWindow();

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        this.owner = owner;


        menuFace = new Rectangle(new Point(0, 0), area);
        this.setPreferredSize(area);

        Dimension btnDim = new Dimension(area.width / 3, area.height / 12);
        backButton = new Rectangle(btnDim);
        menuButton = new Rectangle(btnDim);

        borderStoke = new BasicStroke(BORDER_SIZE, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 0, DASHES, 0);
        borderStoke_noDashes = new BasicStroke(BORDER_SIZE, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND);

        greetingsFont = new Font("Noto Mono", Font.PLAIN, 25);
        gameTitleFont = new Font("Noto Mono", Font.BOLD, 40);
        creditsFont = new Font("Monospaced", Font.PLAIN, 10);
        buttonFont = new Font("Monospaced", Font.PLAIN, backButton.height - 2);


    }


    public void paint(Graphics g) {
        drawMenu((Graphics2D) g);
    }

    /**
     * draw the menu
     * @param g2d
     */
    public void drawMenu(Graphics2D g2d) {

        drawContainer(g2d);

        /*
        all the following method calls need a relative
        painting directly into the HomeMenu rectangle,
        so the translation is made here so the other methods do not do that.
         */
        Color prevColor = g2d.getColor();
        Font prevFont = g2d.getFont();

        double x = menuFace.getX();
        double y = menuFace.getY();

        g2d.translate(x, y);

        //methods calls
        drawText(g2d);
        drawButton(g2d);
        //end of methods calls

        g2d.translate(-x, -y);
        g2d.setFont(prevFont);
        g2d.setColor(prevColor);
    }

    /**
     * CSS for thecontainer
     * @param g2d
     */

    private void drawContainer(Graphics2D g2d) {
        Color prev = g2d.getColor();

        g2d.setColor(BG_COLOR);
        g2d.fill(menuFace);

        Stroke tmp = g2d.getStroke();

        g2d.setStroke(borderStoke_noDashes);
        g2d.setColor(DASH_BORDER_COLOR);
        g2d.draw(menuFace);

        g2d.setStroke(borderStoke);
        g2d.setColor(BORDER_COLOR);
        g2d.draw(menuFace);

        g2d.setStroke(tmp);

        g2d.setColor(prev);

        Image picture = Toolkit.getDefaultToolkit().getImage("Info-Img.jpg"); //background picture
        g2d.drawImage(picture, 0, 0, this);

    }

    /**
     * CSS for the text
     * @param g2d
     */

    private void drawText(Graphics2D g2d) {

        g2d.setColor(TEXT_COLOR);

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D greetingsRect = greetingsFont.getStringBounds(GREETINGS, frc);
        Rectangle2D gameTitleRect = gameTitleFont.getStringBounds(GAME_TITLE, frc);
        Rectangle2D creditsRect = creditsFont.getStringBounds(CREDITS, frc);

        int sX, sY;

        sX = (int) (menuFace.getWidth() - greetingsRect.getWidth()) / 2;
        sY = (int) (menuFace.getHeight() / 4);

        g2d.setFont(greetingsFont);
        g2d.drawString(GREETINGS, sX, sY);

        sX = (int) (menuFace.getWidth() - gameTitleRect.getWidth()) / 2;
        sY += (int) gameTitleRect.getHeight() * 1.1;//add 10% of String height between the two strings

        g2d.setFont(gameTitleFont);
        g2d.drawString(GAME_TITLE, sX, sY);

        sX = (int) (menuFace.getWidth() - creditsRect.getWidth()) / 2;
        sY += (int) creditsRect.getHeight() * 1.1;

        g2d.setFont(creditsFont);
        g2d.drawString(CREDITS, sX, sY);


    }

    /**
     * CSS for the button
     * @param g2d g2d
     */

    private void drawButton(Graphics2D g2d) {

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D txtRect = buttonFont.getStringBounds(BACK_TEXT, frc);
        Rectangle2D mTxtRect = buttonFont.getStringBounds(MENU_TEXT, frc);

        g2d.setFont(buttonFont);

        int x = (menuFace.width - backButton.width) / 2;
        int y = (int) ((menuFace.height - backButton.height) * 0.8);

        backButton.setLocation(x, y);

        x = (int) (backButton.getWidth() - txtRect.getWidth()) / 2;
        y = (int) (backButton.getHeight() - txtRect.getHeight()) / 2;

        x += backButton.x;
        y += backButton.y + (backButton.height * 0.9);


        if (backClicked) {
            Color tmp = g2d.getColor();
            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(backButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(BACK_TEXT, x, y);
            g2d.setColor(tmp);
        } else {
            g2d.draw(backButton);
            g2d.drawString(BACK_TEXT, x, y);
        }

        x = backButton.x;
        y = backButton.y;

        y *= 1.2;

        menuButton.setLocation(x, y);


        x = (int) (menuButton.getWidth() - mTxtRect.getWidth()) / 2;
        y = (int) (menuButton.getHeight() - mTxtRect.getHeight()) / 2;

        x += menuButton.x;
        y += menuButton.y + (backButton.height * 0.9);

        if (menuClicked) {
            Color tmp = g2d.getColor();

            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(menuButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(MENU_TEXT, x, y);
            g2d.setColor(tmp);
        } else {
            g2d.draw(menuButton);
            g2d.drawString(MENU_TEXT, x, y);
        }

    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if (backButton.contains(p)) {
            owner.enableHomeScreen();

        } else if (menuButton.contains(p)) {
            System.out.println("Goodbye " + System.getProperty("user.name"));
            System.exit(0);
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if (backButton.contains(p)) {
            backClicked = true;
            repaint(backButton.x, backButton.y, backButton.width + 1, backButton.height + 1);

        } else if (menuButton.contains(p)) {
            menuClicked = true;
            repaint(menuButton.x, menuButton.y, menuButton.width + 1, menuButton.height + 1);
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if (backClicked) {
            backClicked = false;
            repaint(backButton.x, backButton.y, backButton.width + 1, backButton.height + 1);
        } else if (menuClicked) {
            menuClicked = false;
            repaint(menuButton.x, menuButton.y, menuButton.width + 1, menuButton.height + 1);
        }
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }


    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if (backButton.contains(p) || menuButton.contains(p))
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else
            this.setCursor(Cursor.getDefaultCursor());

    }
}
