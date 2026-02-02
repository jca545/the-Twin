package com.group5.twins_game;

/**
 * Enum class for the four direction,
 * Up, Down, Left, Right
 *
 * @author Jin Yang
 *
 * @see Movable
 * @see Player
 * @see Enemy
 */
public enum Direction {
    Up(0), Down(1), Left(2), Right(3);

    private final int value;

    // Constructor
    Direction(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    private static final int[] X_DISPLACEMENTS = {0, 0, -1, 1};
    private static final int[] Y_DISPLACEMENTS = {-1, 1, 0, 0};

    public int getXDisplacement() {
        return X_DISPLACEMENTS[value];
    }

    public int getYDisplacement() {
        return Y_DISPLACEMENTS[value];
    }
}
