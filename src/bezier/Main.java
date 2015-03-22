/* 
 * Copyright (C) 2015 developer-jox <developer.jox@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package bezier;

/**
 *
 * @author developer-jox <developer.jox@gmail.com>
 */
import javax.swing.*;

public class Main extends JFrame {

    Bezier panel = new Bezier();

    public Main() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Nice is by tyhe cote azur");
        add( "Center", panel);
        pack();
        setVisible(true);

        panel.run();
    }

    public static void main(String[] args) {
        new Main();
    }

}
