import java.io.IOException;

public class Client 
{
    public class Context
    {
        private State state;
        private char c;

        public void setState(State state)
        {
            this.state = state;
        }

        public State getState()
        {
            return state;
        }

        public void setChar(char c)
        {
            this.c = c;
        }

        public void next()
        {
            state.next(this, c);
        }
    }

    public abstract class State
    {
        private boolean accepting;

        public State(boolean accepting)
        {
            this.accepting = accepting;
        }

        public boolean accepting()
        {
            return accepting;
        }

        public abstract void next(Context context, char c);
    }

    public class StateA extends State
    {
        public StateA(boolean accepting) 
        {
            super(accepting);
        }

        public void next(Context context, char c)
        {
            switch (c) 
            {
                case 'a':
                    context.setState(stateB);
                    break;
                case 'b':
                    context.setState(stateE);
                    break;
                default:
                    context.setState(stateE);
                    break;
            }
        }
    }

    public class StateB extends State
    {
        public StateB(boolean accepting) 
        {
            super(accepting);
        }

        public void next(Context context, char c)
        {
            switch (c) 
            {
                case 'a':
                    context.setState(stateA);
                    break;
                case 'b':
                    context.setState(stateB);
                    break;
                default:
                    context.setState(stateE);
                    break;
            }
        }
    }

    public class StateE extends State
    {
        public StateE(boolean accepting) 
        {
            super(accepting);
        }

        public void next(Context context, char c) { }
    }

    public State stateA = new StateA(true);
    public State stateB = new StateB(false);
    public State stateE = new StateE(false);
    public Context context = new Context();

    public static void main(String args[])
    {
        Client client = new Client();
        client.context.setState(client.stateA);

        char c;
        try 
        {
            while ((c = (char)System.in.read()) != '\r')
            {
                client.context.setChar(c);
                client.context.next();
            }
            if (client.context.getState().accepting())
            {

            }
            else
            {

            }
        } 
        catch (IOException e) 
        {   
            e.printStackTrace();
        }
    }
}
