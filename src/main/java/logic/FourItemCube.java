package logic;

public final class FourItemCube
{
    public static void sleep(int millis)
    {
        try
        {
            Thread.sleep(millis);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    public static short[] getOutputValues(int level, int row, int col)
    {
        return getOutputValues((level << 4) + (row << 2) + col);
    }

    public static short[] getOutputValues(int address)
    {
        return getOutputValues(address >>> 3, address % 8);
    }

    /**
     * Output value for control[c0-c3] and data[d0-d7] pins to an item in the 8x8 matrix
     *
     * @param row 0-7 row index
     * @param col 0-7 column index
     * @return
     */
    public static short[] getOutputValues(int row, int col)
    {
        if (row > 8 || col > 8)
        {
            throw new RuntimeException("Row or column exceeded value of 8");
        }

        short cout = 0;
        short dout = 0;

        // apply row data first
        cout |= 0x1 << row / 4;
        dout |= 0x1 << row % 4;

        // shift to left, to apply the col data which will be on the lower bits
        cout <<= 2;
        dout <<= 4;

        // adding the col values using bitwise inclusive OR operation, to preserve the row data
        cout |= 0x1 << col / 4;
        dout |= 0x1 << col % 4;

        return new short[]{cout, dout};
    }

    public static RowCol getRowColForAddress(int rows, int cols, int address)
    {
        int row = address / cols;
        int col = address % cols;

        if (row > rows || col > cols)
        {
            throw new RuntimeException("Selection exceeds rows or cols");
        }

        return new RowCol(row, col);
    }

    public static int getAddress(int rows, int cols, RowCol selection)
    {
        if (selection.getRow() > rows || selection.getCol() > cols)
        {
            throw new RuntimeException("Selection exceeds rows or cols");
        }

        return cols * selection.getRow() + selection.getCol();
    }
}
