package logic;

import junit.framework.TestCase;
import pport.ParallelPort;
import pport.ParallelPortRegister;

public class Test extends TestCase
{
    public void testName() throws Exception
    {
        long start = System.nanoTime();

        ParallelPort writer = new ParallelPort();
        for (int i = 0; i < 3; i++) {
            short[] values = FourItemCube.getOutputValues(i);

            writer.write(ParallelPortRegister.DATA, values[1]);
            writer.write(ParallelPortRegister.CONTROL, values[0]);
        }

        System.out.println(String.format("Finished in %d ms", System.nanoTime() - start));
    }

    public void test2() throws Exception
    {
        long start = System.nanoTime();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                short[] index2DBased = FourItemCube.getOutputValues(i, j);
                int addressInMatrix = i * 8 + j;
                short[] addressBased = FourItemCube.getOutputValues(addressInMatrix);
                int i1 = addressInMatrix % 16;
                short[] index3DBased = FourItemCube.getOutputValues(addressInMatrix >>> 4, i1 >>> 2, i1 % 4);

                assertEquals(index2DBased[0], addressBased[0]);
                assertEquals(index2DBased[1], addressBased[1]);
                assertEquals(index3DBased[0], addressBased[0]);
                assertEquals(index3DBased[1], addressBased[1]);
//                FourItemCube.sleep(1);
            }
        }

        short[] outputValues = FourItemCube.getOutputValues(3);
        short[] outputValues1 = FourItemCube.getOutputValues(57);

        System.out.println(String.format("Finished in %d ns", System.nanoTime() - start));
    }

    public void test3() throws Exception
    {
        ParallelPort port = new ParallelPort();

        for (int i = 0; i < 10; i++) {

//            port.write(ParallelPortRegister.DATA, (short) (i%2));
            port.write(ParallelPortRegister.CONTROL, (short) ((i+1)%2));
            port.read(ParallelPortRegister.CONTROL);
            FourItemCube.sleep(500);
        }
    }

    public void testControlInversion() throws Exception
    {
        for (int i = 0; i < 16; i++)
        {
            System.out.println(String.format("i=%d [%s] inverted=[%s]", i, Integer.toBinaryString(i), Integer.toBinaryString(i^11)));

        }
    }
}
