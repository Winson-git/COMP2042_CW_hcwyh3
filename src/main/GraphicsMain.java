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
package main;

import main.controller.GameFrame;

import javax.sound.sampled.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * This class runs the game
 *
 * @author winson
 */
public class GraphicsMain {

    /**
     *
     * @param args args
     * @throws UnsupportedAudioFileException
     * @throws IOException
     * @throws LineUnavailableException
     *
     * This method Allow Audio play behind the background
     */

    public static void main(String[] args)throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        EventQueue.invokeLater(() -> new GameFrame().initialize());

        File file = new File("BG_audio.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);

        clip.start();

    }

}
