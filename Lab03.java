 
 
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import javax.swing.JFrame;
 
public class LAB03_01 implements GLEventListener {
    private GLU glu;
    @Override
    public void display(GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();
 
        MPL(gl, -70.0f, 70.0f, -20.0f, 70.0f);
        MPL(gl, -70.0f, 0.0f, -70.0f, 35.0f);
        MPL(gl, -70.0f, 35.0f, -70.0f, 70.0f);
        MPL(gl, -20.0f, 0.0f, -20.0f, 35.0f);
        MPL(gl, -70.0f, 35.0f, -20.0f, 35.0f);
        MPL(gl, -70.0f, 0.0f, -20.0f, 0.0f);
 
 
 
        MPL(gl, 00.0f, 70.0f, 50.0f, 70.0f);
        MPL(gl, 0.0f, 0.0f, 0.0f, 35.0f);
        MPL(gl, 0.0f, 35.0f, 0.0f, 70.0f);
        MPL(gl, 50.0f, 0.0f, 50.0f, 35.0f);
        MPL(gl, 50.0f, 35.0f, 50.0f, 70.0f);
        MPL(gl, 0.0f, 0.0f, 50.0f, 0.0f);
 
    }
    @Override
    public void dispose(GLAutoDrawable arg0) {
        //method body
    }
 
    @Override
    public void init(GLAutoDrawable gld) {
        GL2 gl = gld.getGL().getGL2();
        glu = new GLU();
 
        gl.glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
        gl.glViewport(-100, -50, 50, 100);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluOrtho2D(-100.0, 100.0, -100.0, 100.0);
    }
    @Override
    public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3, int arg4) {
        // method body
    }
    public void MPL(GL2 gl,float x1, float y1, float x2, float y2) {
        int zone = findZone(x1,y1,x2,y2);
        float p = 0,q = 0,r = 0,s=0;
 
        // converting to zone zero
        if(zone == 0){
            p=x1;           q=y1;                r=x2;                s=y2;
        }
        else if(zone ==1){
            p=y1;                q=x1;                r=y2;                s=x2;
        }
        else if(zone ==2){
            p=y1;                q=-x1;                r=y2;                s=-x2;
        }
        else if(zone ==3){
            p=-x1;               q=y1;                  r=-x2;            s=y2;
        }
        else if(zone ==4){
            p=-x1;                q=-y1;                r=-x2;                s=-y2;
        }
        else if(zone==5) {
            p=-y1;                q=-x1;                r=-y2;                s=-x2;
        }
        else if(zone == 6){
            p=-y1;                q=x1;                r=-y2;                s=x2;
        }
        else if(zone ==7){
            p=x1;                q=-y1;                r=x2;                s=-y2;
        }
        drawLIne(gl,p, q, r,s,zone);
    }
    public int findZone(float x1, float y1, float x2, float y2){
        float dx = x2-x1;
        float dy= y2-y1;
 
        if (Math.abs(dx) >= Math.abs(dy)) {
            if (dy >= 0 && dx >= 0)
                return 0;
            else if (dx < 0 && dy >= 0)
                return 3;
            else if (dx < 0 && dy < 0)
                return 4;
            else return 7;
        } else {
            if (dy >= 0 && dx >= 0)
                return 1;
            else if (dx < 0 && dy >= 0)
                return 2;
            else if (dx < 0 && dy < 0)
                return 5;
            else return 6;
        }
    }
    public void drawLIne(GL2 gl, float p, float q, float r, float s,int zone){
        float dx=r-p;
        float dy=s-q;
        float D=2*dy-dx;
        float NE=2*(dy-dx);
        float E=2*dy;
        float x=p,y=q;
        float[] original = new float[2];
 
        while(x<=r){
            original=zoneOriginal(x,y,zone);//convert to original zone
           // System.out.println(x+"  "+r);
            gl.glBegin(GL2.GL_POINTS); // drawing pixel
            gl.glVertex2d(original[0], original[1]);
          //  System.out.println(original[0]+" blahblah "+original[1]);
            gl.glEnd();
            x++;
            if (D > 0) {
                y++;
                D+=NE;
            }
            else{
                D+=E;
            }
        }
    }
    public float[] zoneOriginal(float x, float y,int zone){
        float u=0,v=0;
        if(zone ==0){
            u=x;    v=y;
        }
        else if(zone==1){
            u=y;    v=x;
        }
        else if(zone == 2){
            u=-y;   v=x;
        }
        else if(zone ==3){
            u=-x;   v=y;
        }
        else if(zone == 4){
            u=-x;   v=-y;
        }
        else if(zone == 5){
            u=-y;   v=-x;
        }
        else if(zone == 6){
            u =y;   v=-x;
        }
        else if(zone == 7){
            u =x;   v=-y;
        }
        float a[]=new float[]{u,v};
        return a;
    }
 
 
    public static void main(String[] args) {
        //getting the capabilities object of GL2 profile
        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        // The canvas
        final GLCanvas glcanvas = new GLCanvas(capabilities);
        LAB03_01 l = new LAB03_01();
        glcanvas.addGLEventListener(l);
        glcanvas.setSize(400, 400);
        //creating frame
        final JFrame frame = new JFrame ("MIdpoint Line Algorithm");
        //adding canvas to frame
        frame.getContentPane().add(glcanvas);
        frame.setSize(frame.getContentPane().getPreferredSize());
        frame.setVisible(true);
    }//end of main
}
