import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import javax.swing.JFrame;


public class Lab02 implements GLEventListener
{
    private GLU glu;

    public void display(final GLAutoDrawable drawable) {
        final GL2 gl = drawable.getGL().getGL2();



        DDA(gl, -70.0f, 70.0f, -20.0f, 70.0f);
        DDA(gl, -70.0f, 0.0f, -70.0f, 35.0f);
        DDA(gl, -70.0f, 35.0f, -70.0f, 70.0f);
        DDA(gl, -20.0f, 0.0f, -20.0f, 35.0f);
        DDA(gl, -70.0f, 35.0f, -20.0f, 35.0f);
        DDA(gl, -70.0f, 0.0f, -20.0f, 0.0f);



        DDA(gl, 00.0f, 70.0f, 50.0f, 70.0f);
        DDA(gl, 0.0f, 0.0f, 0.0f, 35.0f);
        DDA(gl, 0.0f, 35.0f, 0.0f, 70.0f);
        DDA(gl, 50.0f, 0.0f, 50.0f, 35.0f);
        DDA(gl, 50.0f, 35.0f, 50.0f, 70.0f);
        DDA(gl, 0.0f, 0.0f, 50.0f, 0.0f);


    }

    public void dispose(final GLAutoDrawable arg0) {
    }

    public void init(final GLAutoDrawable gld) {
        final GL2 gl = gld.getGL().getGL2();
        this.glu = new GLU();
        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glViewport(-100, -50, 50, 100);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        this.glu.gluOrtho2D(-100.0, 100.0, -100.0, 100.0);
    }

    public void reshape(final GLAutoDrawable arg0, final int arg1, final int arg2, final int arg3, final int arg4) {
    }

    public void DDA(final GL2 gl, float x1, float y1, float x2, float y2) {
        gl.glPointSize(3.0f);
        gl.glColor3d(1.0, 0.0, 0.0);
        float xNew = x1;
        float yNew = y1;
        float dx = x2 - x1;
        float dy = y2 - y1;
        float m = dy / dx;


        if (m > 1.0f) {
            gl.glBegin(0);

            while (yNew <= y2) {
                gl.glVertex2f(xNew, yNew);
                xNew += 1.0f / m;
                yNew = yNew+1;
            }

            gl.glEnd();
        }
        else {

            gl.glBegin(0);

            while (xNew <= x2) {
                gl.glVertex2f(xNew, yNew);
                xNew = xNew+1;
                yNew += m;
            }

            gl.glEnd();
        }
    }

    public static void main(String[] args) {
        //getting the capabilities object of GL2 profile
        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        // The canvas
        final GLCanvas glcanvas = new GLCanvas(capabilities);
        Lab02 l = new Lab02();
        glcanvas.addGLEventListener(l);
        glcanvas.setSize(600, 600);
        //creating frame
        final JFrame frame = new JFrame ("DDA Alligator");
        //adding canvas to frame
        frame.getContentPane().add(glcanvas);
        frame.setSize(frame.getContentPane().getPreferredSize());
        frame.setVisible(true);
    }
}

