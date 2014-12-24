package pport;

/**
 * Created by IntelliJ IDEA.
 * User: test
 * Date: Aug 15, 2012
 * Time: 6:24:14 PM
 * To change this template use File | Settings | File Templates.
 */
public enum ParallelPortRegister
{
    DATA(0),            // PINs: 2 - 9
    CONTROL(2);         // PINs: ~1, ~14, 16, ~17	[~ indicates a hardware inversion of the bit. = write 1 will get 0 in the pin]

    private int offset;

    ParallelPortRegister(int offset)
    {
        this.offset = offset;
    }

    public int getOffset()
    {
        return offset;
    }
}
