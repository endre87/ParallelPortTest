package pport;



public class LogicalParallelPort extends ParallelPort
{
    public LogicalParallelPort()
    {
    }

    @Override
    public void write(ParallelPortRegister register, short data)
    {
//        if (ParallelPortRegister.CONTROL == register)
//        {
//            data
//        }
        super.write(register, data);
    }
}
