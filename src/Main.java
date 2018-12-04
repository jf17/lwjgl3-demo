package ru.jf17.demo;


import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import java.nio.*;
import org.joml.Matrix4f;
import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Main {

    DataClass data ;
    final  float COEFFICIENT = 0.0010416666666667f;


    // The window handle
    private long window;

    public void run() {
        System.out.println("Hello LWJGL " + Version.getVersion() + "!");

        init();
        HelloSwing swng = new HelloSwing(data);
        swng.setVisible(true);
        loop();

        // Free the window callbacks and destroy the window
        glfwFreeCallbacks(window);
        glfwDestroyWindow(window);

        // Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    private void init() {

        data = new DataClass();

        // Setup an error callback. The default implementation
        // will print the error message in System.err.
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if ( !glfwInit() )
            throw new IllegalStateException("Unable to initialize GLFW");

        // Configure GLFW
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE); // the window will be resizable

        // Create the window
        window = glfwCreateWindow(960, 960, "Test LWJGL 3 + Swing .", NULL, NULL);
        glfwSetWindowPos(window,5,30);
        if ( window == NULL )
            throw new RuntimeException("Failed to create the GLFW window");

        // Setup a key callback. It will be called every time a key is pressed, repeated or released.
        glfwSetKeyCallback(window, (window, key, scancode, action, mods) -> {
            if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
                glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
            else if(key == GLFW_KEY_UP && action == GLFW_RELEASE){
               data.setUp_view_roty(data.getUp_view_roty() + data.getPLUS());
               data.setDown_view_roty(data.getDown_view_roty() + data.getPLUS());
            }else if(key == GLFW_KEY_DOWN && action == GLFW_RELEASE){
               data.setUp_view_roty(data.getUp_view_roty() - data.getPLUS()); ;
               data.setDown_view_roty(data.getDown_view_roty() - data.getPLUS());
            }else if(key == GLFW_KEY_LEFT && action == GLFW_RELEASE){
               data.setRight_view_rotx(data.getRight_view_rotx() - data.getPLUS());
                data.setLeft_view_rotx(data.getLeft_view_rotx() - data.getPLUS());
            }else if(key == GLFW_KEY_RIGHT && action == GLFW_RELEASE){
               data.setRight_view_rotx(data.getRight_view_rotx() + data.getPLUS());
                data.setLeft_view_rotx(data.getLeft_view_rotx() + data.getPLUS());
            }else if(key == GLFW_KEY_Z && action == GLFW_RELEASE){
                data.setDefault();
            }
        });

        // Get the thread stack and push a new frame
        try ( MemoryStack stack = stackPush() ) {
            IntBuffer pWidth = stack.mallocInt(1); // int*
            IntBuffer pHeight = stack.mallocInt(1); // int*

            // Get the window size passed to glfwCreateWindow
            glfwGetWindowSize(window, pWidth, pHeight);

            // Get the resolution of the primary monitor
            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            // Center the window
          //  glfwSetWindowPos(
           //         window,
             //       (vidmode.width() - pWidth.get(0)) / 2,
            //        (vidmode.height() - pHeight.get(0)) / 2
           // );
        } // the stack frame is popped automatically

        // Make the OpenGL context current
        glfwMakeContextCurrent(window);
        // Enable v-sync
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(window);
    }


    // JOML matrices
    Matrix4f projMatrix = new Matrix4f();
    Matrix4f viewMatrix = new Matrix4f();
    Matrix4f modelMatrix = new Matrix4f();
    Matrix4f modelViewMatrix = new Matrix4f();
    // FloatBuffer for transferring matrices to OpenGL
    FloatBuffer fb = BufferUtils.createFloatBuffer(16);

    void renderCenterLines() {
        glBegin(GL_LINES);
        glColor3f( 0.0f, 0.4f, 0.2f ); // цвет
        glVertex2f(-50*COEFFICIENT, 0f);
        glVertex2f(50*COEFFICIENT, 0f);
        glEnd();
        glBegin(GL_LINES);
        glColor3f( 0.0f, 0.4f, 0.2f ); // цвет
        glVertex2f(0, -50*COEFFICIENT);
        glVertex2f(0, 50*COEFFICIENT);
        glEnd();
    }

    void renderSQR(){

        glBegin(GL_POLYGON);
        glColor3f( 0.0f, 0.2f, 0.0f ); // цвет
        glVertex2f(data.getLeft_view_rotx(), data.getDown_view_roty());
        glVertex2f(data.getLeft_view_rotx(), data.getUp_view_roty());
        glVertex2f(data.getRight_view_rotx(), data.getUp_view_roty());
        glVertex2f(data.getRight_view_rotx(), data.getDown_view_roty());
        glEnd();


    }


    private void loop() {
        // This line is critical for LWJGL's interoperation with GLFW's
        // OpenGL context, or any context that is managed externally.
        // LWJGL detects the context that is current in the current thread,
        // creates the GLCapabilities instance and makes the OpenGL
        // bindings available for use.
        GL.createCapabilities();

        // Set the clear color
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        long firstTime = System.nanoTime();

        while ( !glfwWindowShouldClose(window) ) {
            /*
            long thisTime = System.nanoTime();
            float diff = (thisTime - firstTime) / 1E9f;
            // Compute some rotation angle.
            float angle = diff;
            */

            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

            renderCenterLines();
            renderSQR();


            /*
            for (int x = -2; x <= 2; x++) {
                for (int z = -2; z <= 2; z++) {
                    modelMatrix.translation(x * 2.0f, 0, z * 2.0f)
                            .rotateX(angle * (float) Math.toRadians(90))
                            .rotateY(angle * (float) Math.toRadians(90));
                    glLoadMatrixf(viewMatrix.mul(modelMatrix, modelViewMatrix).get(fb));
                    renderCube();
                }
            }
            */




            glfwSwapBuffers(window); // swap the color buffers


            // Poll for window events. The key callback above will only be
            // invoked during this call.
            glfwPollEvents();
        }
    }
    public static void main(String[] args) {

        new Main().run();
    }
}
