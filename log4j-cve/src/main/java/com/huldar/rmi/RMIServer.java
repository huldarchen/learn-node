package com.huldar.rmi;

import javax.naming.Reference;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @author huldarchen
 * @version 1.0
 * @date 2021/12/13 12:25
 */
public class RMIServer {

  public static void main(String[] args) {
    try {
      LocateRegistry.createRegistry(1009);
      Registry registry = LocateRegistry.getRegistry();
      System.out.println("Create RMI registry on port 1099");
      new Reference("com.huldar.rmi.EvilObj", "com.huldar.rmi.EvilObj", "");
    } catch (RemoteException e) {
      e.printStackTrace();
    }
  }
}
