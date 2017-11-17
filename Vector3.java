package Vector3;

public class Vector3 {
    public float x;
    public float y;
    public float z;

    public Vector3(float x, float y, float z) {
        this.set(x,y,z);
    }

    public Vector3(Vector3 v) {
        this.set(v);
    }

    public Vector3() {
        this.set(0,0,0);
    }

    public Vector3 set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }

    public Vector3 set(Vector3 v) {
        x = v.x;
        y = v.y;
        z = v.z;
        return this;
    }

    public Vector3 normalize() {
        float norm = (float) Math.sqrt(x*x + y*y + z*z);
        x = x / norm;
        y = y / norm;
        z = z / norm;
        return this;
    }
}