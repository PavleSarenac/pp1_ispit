import java.io.IOException;

public class FiniteStateMachineObjectOriented 
{
    public class Context
    {
        private State currentState;
        private char currentInputSymbol;

        public void setCurrentState(State state)
        {
            currentState = state;
        }

        public State getCurrenState()
        {
            return currentState;
        }

        public void setCurrentInputSymbol(char inputSymbol)
        {
            currentInputSymbol = inputSymbol;
        }

        public void next()
        {
            currentState.next(this, currentInputSymbol);
        }
    }

    public abstract class State
    {
        public boolean isValidEndState;

        public State(boolean isValidEndState)
        {
            this.isValidEndState = isValidEndState;
        }

        public boolean isValidEndState()
        {
            return isValidEndState;
        }

        public abstract void next(Context context, char currentInputSymbol);
    }

    public class StateA extends State
    {
        public StateA(boolean isValidEndState) 
        {
            super(isValidEndState);
        }

        public void next(Context context, char currentInputSymbol)
        {
            switch (currentInputSymbol) 
            {
                case 'a':
                    context.setCurrentState(stateB);
                    break;
                case 'b':
                    context.setCurrentState(stateE);
                    break;
                default:
                    context.setCurrentState(stateE);
                    break;
            }
        }
    }

    public class StateB extends State
    {
        public StateB(boolean isValidEndState) 
        {
            super(isValidEndState);
        }

        public void next(Context context, char currentInputSymbol)
        {
            switch (currentInputSymbol) 
            {
                case 'a':
                    context.setCurrentState(stateA);
                    break;
                case 'b':
                    context.setCurrentState(stateB);
                    break;
                default:
                    context.setCurrentState(stateE);
                    break;
            }
        }
    }

    public class StateE extends State
    {
        public StateE(boolean isValidEndState) 
        {
            super(isValidEndState);
        }

        public void next(Context context, char currentInputSymbol) { }
    }

    public Context context = new Context();
    public StateA stateA = new StateA(true);
    public StateB stateB = new StateB(false);
    public StateE stateE = new StateE(false);

    public static void main(String args[])
    {
        try
        {
            FiniteStateMachineObjectOriented finiteStateMachine = new FiniteStateMachineObjectOriented();
            finiteStateMachine.context.setCurrentState(finiteStateMachine.stateA);
            char currentInputSymbol;
            while ((currentInputSymbol = (char)System.in.read()) != '\r')
            {
                finiteStateMachine.context.setCurrentInputSymbol(currentInputSymbol);
                finiteStateMachine.context.next();;
            }

            if (finiteStateMachine.context.getCurrenState().isValidEndState())
            {
                System.out.println("Input is a VALID sequence.");
            }
            else
            {
                System.out.println("Input is an INVALID sequence.");
            }
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
