/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pacman;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author rouaa
 */
public class Ghost extends Componant {

    public Ghost(int x, int y) {
        this.x = x;
        this.y = y;
        direction = 'L';

    }

    public boolean choice() {

        if (x % cellSize == 0 && y % cellSize == 0) {
            return true;
        } else {
            return false;
        }

    }

    public char selectDirection() {
        int random;
        int newx = x, newy = y;
        Set<Character> mySet = new HashSet<Character>();

        char backwards = 'R';
        switch (direction) {
            case 'L':
                backwards = 'R';
                break;
            case 'R':
                backwards = 'L';
                break;
            case 'U':
                backwards = 'D';
                break;
            case 'D':
                backwards = 'U';
                break;
        }
        char newDirection = backwards;

        while (newDirection == backwards || !isValid(newx, newy)) {

            if (mySet.size() == 3) {
                mySet.clear();
                newDirection = backwards;
                break;
            }

            random = (int) (Math.random() * 4) + 1;
            if (random == 1) {
                newDirection = 'L';
                newx -= speed;
            } else if (random == 2) {

                newDirection = 'R';
                newx += cellSize;
            } else if (random == 3) {

                newDirection = 'U';
                newy -= speed;
            } else if (random == 4) {

                newDirection = 'D';
                newy += cellSize;
            }

            if (newDirection != backwards) {
                mySet.add(newDirection);
            }
            
            index = random %2;
            
        }
        return newDirection;
    }

    public void move() {
        if (choice()) {
            direction = selectDirection();
        }
        switch (direction) {
            case 'L':
                if (isValid(x - speed, y)) {
                    x -= speed;
                }
                break;
            case 'R':
                if (isValid(x + cellSize, y)) {
                    x += speed;
                }
                break;
            case 'U':
                if (isValid(x, y - speed)) {
                    y -= speed;
                }
                break;
            case 'D':
                if (isValid(x, y + cellSize)) {
                    y += speed;
                }
                break;
        }
    }
}
