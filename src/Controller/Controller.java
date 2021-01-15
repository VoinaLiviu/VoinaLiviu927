package Controller;

import Exceptions.ExecutionStackException;
import Model.ADTs.MyList;
import Model.ADTs.MyStack;
import Model.ProgramState.ProgramState;
import Model.Stmt.IStatement;
import Model.Value.IValue;
import Model.Value.RefValue;
import Repository.IRepository;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;

public class Controller {
    private IRepository repository;
    private boolean displayFlag;
    private ExecutorService executor;

    public Controller(IRepository repo) {
        repository = repo;
        displayFlag = false;
    }

    public void setDisplayFlag(boolean flag){
        displayFlag = flag;
    }

    public boolean getDisplayFlag(){
        return displayFlag;
    }

    public ProgramState oneStepExecution(ProgramState programState){
        MyStack<IStatement> executionStack = programState.getExecutionStack();
        if(executionStack.isEmpty()) throw new ExecutionStackException("Execution stack is empty");
        IStatement statement = executionStack.pop();
        return statement.execute(programState);
    }


    public void oneStepForAllPrograms(List<ProgramState> states) throws RuntimeException{
        states.forEach(state -> {
            try {
                repository.logPrgStateExec(state);
            } catch (RuntimeException e) {}
        });
        List<Callable<ProgramState>> callableList = states.stream()
                .map( p -> (Callable<ProgramState>)(p::oneStep))
                .collect(Collectors.toList());

        List<ProgramState> newStates = null;
        try {
            newStates = this.executor.invokeAll(callableList).stream()
                    .map(futureObj -> {
                        try{
                            return futureObj.get();
                        }
                        catch (RuntimeException e) {} catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        }
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        states.addAll(newStates);
        states.forEach(state -> {
            try {
                repository.logPrgStateExec(state);
                System.out.println(state);
            }
            catch (RuntimeException e){}
        });
        repository.setPrgList(states);
    }

    public void logProgramState(ProgramState programState){
        repository.logPrgStateExec(programState);
    }

    public Map<Integer, IValue> unsafeGarbageCollector(List<Integer> symTableAddr, Map<Integer, IValue> heap){
        return heap.entrySet().stream()
                .filter(e -> symTableAddr.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
    public List<Integer> getAddrFromSymTable(Collection<IValue> symTableValues){
        return symTableValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> {RefValue v1 = (RefValue)v; return v1.getAddress();})
                .collect(Collectors.toList());
    }

    public MyList<ProgramState> removeCompletedPrograms(MyList<ProgramState> inPrgList){
        return inPrgList.stream()
                .filter(p -> p.isNotCompleted())
                .collect(Collectors.toList());
    }

}
