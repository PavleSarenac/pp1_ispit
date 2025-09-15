import java.io.IOException;

public class FiniteStateMachineProceduralmplicit 
{
    public enum State
    {
        A(true),
        B(false),
        E(false);

        public boolean isValidEndState;

        State(boolean isValidEndState)
        {
            this.isValidEndState = isValidEndState;
        }
    }

    State currentState = State.A;

    public void processInputSymbol(char inputSymbol)
    {
        switch (currentState) {
            case A:
                if (inputSymbol == 'a')
                {
                    currentState = State.B;
                }
                else
                {
                    currentState = State.E;
                }
                break;
            case B:
                if (inputSymbol == 'a')
                {
                    currentState = State.A;
                }
                else if (inputSymbol == 'b')
                {
                    currentState = State.B;
                }
                else
                {
                    currentState = State.E;
                }
                break;
            case E:
                break;
        }
    }

    public static void main(String[] args)
    {
        try
        {
            FiniteStateMachineProceduralmplicit finiteStateMachine = new FiniteStateMachineProceduralmplicit();
            char inputSymbol;

            while ((inputSymbol = (char)System.in.read()) != '\r')
            {
                finiteStateMachine.processInputSymbol(inputSymbol);
            }

            if (finiteStateMachine.currentState.isValidEndState)
            {
                System.out.println("Input is a VALID sequence.");
            }
            else
            {
                System.out.println("Input is an INVALID sequence.");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
