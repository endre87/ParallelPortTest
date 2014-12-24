package logic;

public class RowCol
{
    private Integer row;
    private Integer col;

    public RowCol(Integer col, Integer row)
    {
        this.col = col;
        this.row = row;
    }

    public int getRow()
    {
        return row;
    }

    public int getCol()
    {
        return col;
    }

    public void setRow(int row)
    {
        this.row = row;
    }

    public void setCol(int col)
    {
        this.col = col;
    }
}