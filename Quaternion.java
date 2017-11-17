package Quaternion;
import Vector3.Vector3;

public class Quaternion {
    public float x;
    public float y;
    public float z;
    public float w;

    public Quaternion(float x, float y, float z, float w) {
        this.set(x,y,z,w);
    }

    public Quaternion() {
        this.set(0f,0f,0f,1f);
    }

    public Quaternion(Quaternion q) {
        this.set(q);
    }

    public Quaternion set(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
        return this;
    }

    public Quaternion set(Quaternion q) {
        this.x = q.x;
        this.y = q.y;
        this.z = q.z;
        this.w = q.w;
        return this;
    }

    public Quaternion normalize() {
        float norm = (float) Math.sqrt(x*x + y*y + z*z + w*w);
        x = x / norm;
        y = y / norm;
        z = z / norm;
        w = w / norm;
        return this;
    }

    public Quaternion conjugate() {
        return new Quaternion(-x, -y, -z, w);
    }

    public Quaternion rotateVector () {
        return this;
    }

    public Vector3 eulerAngles() {
        Vector3 eulerAngles = new Vector3();

		float sqw = w*w;
		float sqx = x*x;
		float sqy = y*y;
        float sqz = z*z;
        
		eulerAngles.x = (float) Math.atan2(2.0 * (x*y + z*w),(sqx - sqy - sqz + sqw));
		eulerAngles.y = (float) Math.atan2(2.0 * (y*z + x*w),(-sqx - sqy + sqz + sqw));
		eulerAngles.z = (float) Math.asin(-2.0 * (x*z - y*w));

        return eulerAngles;
    }
    
    public Quaternion cross(Quaternion q) {
        Quaternion q1 = new Quaternion(this);
        Quaternion q2 = new Quaternion(q);

        q.w = q1.w * q2.w - q1.x * q2.x - q1.y * q2.y - q1.z * q2.z;
        q.x = q1.w * q2.x + q1.x * q2.w + q1.y * q2.z - q1.z * q2.y;
        q.y = q1.w * q2.y + q1.y * q2.w + q1.z * q2.x - q1.x * q2.z;
        q.z = q1.w * q2.z + q1.z * q2.w + q1.x * q2.y - q1.y * q2.x;
    
        return q;
    }

    public Vector3 rotateVector(Vector3 v) {
        Quaternion q = new Quaternion(v.x, v.y, v.z, 0);
        Quaternion inverse_q = q.conjugate();
        Quaternion cross_product = cross(q);
        q = cross_product.cross(inverse_q);
        v = new Vector3(q.x,q.y,q.z);
        return v;
    }

}