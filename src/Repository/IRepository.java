package Repository;

import Model.ADTs.MyList;
import Model.ProgramState.ProgramState;

public interface IRepository {
    void setLogFilePath(String filePath);
    void addProgramState(ProgramState programState);
    ProgramState getCrtPrg();
    void logPrgStateExec(ProgramState programState) throws RuntimeException;
    List<ProgramState> getPrgList();
    void setPrgList(List<ProgramState> prgList);
}
