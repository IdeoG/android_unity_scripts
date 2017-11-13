using System.IO.Ports;

public class serial : MonoBehaviour {
private SerialPort sp;
void Start () {
    sp = new SerialPort ("COM1",115200, Parity.None, 8, StopBits.One);
    sp.Open ();
    sp.ReadTimeout = 1;
}

void Update() {
    if (sp.IsOpen) {
        try {
            sp.ReadByte();  // here, in PC, call the function to use the serial data
        }
        catch ( System.Exception ) {
                    //here in Android is the problem, the funcion call the exception
        }
    }
}

void readData() {
    if ((sp != null) && (sp.IsOpen)) {
        byte tmp;
        string data = "";
        string avalues="";
        tmp = (byte) sp.ReadByte();
        while(tmp !=255) {
            data+=((char)tmp);
            tmp = (byte) sp.ReadByte();
            if((tmp=='>') && (data.Length > 30)){
                avalues = data;
                parseValues(avalues);
                data="";
            }
        }
    }
}