package by.it.pozdeev.Calc;

public class Vector extends Var {

    private double[] value;

    public Vector(double[] value) {
        this.value = new double[value.length];
        System.arraycopy(value, 0, this.value, 0, value.length);
    }

    Vector(Vector vector) {
        this(vector.value);
    }
    Vector(String strVector) {
        String[] str = strVector.substring(1, strVector.length() - 1).split(",");
        this.value = new double[str.length];
        for (int i = 0; i < this.value.length; i++) {
            this.value[i] = Double.parseDouble(str[i]);
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append('{');
        for (int i = 0; i < value.length; i++) {
            if (i > 0) result.append(", ");
            result.append(value[i]);
        }
        result.append('}');
        return result.toString();
    }

    public double[] getValue() {
        return value;
    }

    public double getValue(int i) {
        return value[i];
    }


    public Var add(Var other) {
        double[] result = new double[this.value.length];
        if (other instanceof Scalar) {
            for (int i = 0; i < result.length; i++) {
                result[i] = this.value[i] + ((Scalar) other).getValue();
            }
            return new Vector(result);
        } else if (other instanceof Vector) {
            for (int i = 0; i < result.length; i++) {
                result[i] = this.value[i] + ((Vector) other).value[i];
            }
            return new Vector(result);
        } else return other.add(this);
    }

    @Override
    public Var sub(Var other) {
        double[] result = new double[this.value.length];
        if (other instanceof Scalar) {
            for (int i = 0; i < result.length; i++) {
                result[i] = this.value[i] - ((Scalar) other).getValue();
            }
            return new Vector(result);
        } else if (other instanceof Vector) {
            for (int i = 0; i < result.length; i++) {
                result[i] = this.value[i] - ((Vector) other).value[i];
            }
            return new Vector(result);
        } else return super.sub(other);
    }

    @Override
    public Var mul(Var other) {
        double[] result = new double[this.value.length];
        if (other instanceof Scalar) {
            for (int i = 0; i < result.length; i++) {
                result[i] = this.value[i] * ((Scalar) other).getValue();
            }
            return new Vector(result);
        } else if (other instanceof Vector && this.value.length == ((Vector) other).value.length) {
              for (int i = 0; i < result.length; i++) {
                result[i] = this.value[i] * ((Vector) other).value[i];
            }
            return new Vector(result);
        } else return super.mul(other);
    }

    @Override
    public Var div(Var other)throws ArithmeticException {
        double[] result = new double[this.value.length];
        if (other instanceof Scalar) {
            for (int i = 0; i < result.length; i++) {
                if (((Scalar) other).getValue() == 0) throw new ArithmeticException("Division by zero");
                else result[i] = this.value[i] / ((Scalar) other).getValue();
            }
            return new Vector(result);
        } else return super.div(other);
    }
}
