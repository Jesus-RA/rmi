import java.io.OutputStream;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface FileInterface extends Remote {
    public void setFileName(String name) throws RemoteException;
    public String getFileName() throws RemoteException;
    public byte[] downloadFile(String fileName) throws RemoteException;
    public int countLines () throws RemoteException;
    public String[] reader() throws RemoteException;
    public int numeroDeVocales(String frase) throws RemoteException;
    public void write(OutputStream output) throws RemoteException;
    public String[] print() throws RemoteException;
    public boolean copy(String newFile) throws RemoteException;
    public boolean backup(String backup) throws RemoteException;
    public boolean rename(String name) throws RemoteException;
    public boolean delete() throws RemoteException;
}