/**
 * Auto Generated Java Class.
 */

import java.io.File;
public class XmlLoader extends javax.swing.JFileChooser {
  
 // File chosen;
  
  public XmlLoader() { 
    this.showOpenDialog(null);
    
  }
  public File getFile(){
   return this.getSelectedFile(); 
  }
  
}
