package pport;

import hardware.jnpout32.ioPort;

public class ParallelPort
{
    public final static short DEFAULT_PORT_ADDRESS = 0x378;

    private final short PORT_ADDRESS;

    /**
     * Parallel port CONTROL pins 1, 14, 17 are inverted, this means writing 1 to one of these pins will result in 0 at the output.
     * Ex. writing to control registry the binary value 'a' the output will be 'b'
     * a = 0000
     * b = 1011
     * <p/>
     * By setting this to true we get from:
     * a = 0000
     * b = 0000
     */
    private final boolean LOGICAL_CONTROL_VALUE;

    private ioPort port;

    public ParallelPort(short portAddress, boolean logicalControlValue)
    {
        this.PORT_ADDRESS = portAddress;
        this.LOGICAL_CONTROL_VALUE = logicalControlValue;
        this.port = new ioPort();
    }

    public ParallelPort()
    {
        this(DEFAULT_PORT_ADDRESS, true);
    }

    public void write(ParallelPortRegister register, short data)
    {
        if (ParallelPortRegister.CONTROL == register && LOGICAL_CONTROL_VALUE) {
            // Pins 1, 14, 17 must be inverted to get the logical value
            // XOR pattern = 1011 (11)
            // 1111 XOR 1011 = 0100
            // 1001 XOR 1011 = 0010
            data ^= 11;
        }

        short portAddress = (short) (PORT_ADDRESS + register.getOffset());

        this.port.Out32(portAddress, data);

        log("Writing to", portAddress, data);
    }

    public short read(ParallelPortRegister register)
    {
        short portAddress = (short) (PORT_ADDRESS + register.getOffset());

        short read = this.port.Inp32(portAddress);

        log("Reading from", portAddress, read);

        return read;
    }

    private void log(String pre, int port, int data)
    {
        System.out.println(String.format("%s port 0x%s data: %d [binary: %s]", pre, Integer.toHexString(port), data, Integer.toBinaryString(data)));
    }
}
