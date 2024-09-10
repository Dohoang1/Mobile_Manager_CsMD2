package entities;

public class MobileCase extends Product {
    private String useFor;

    private MobileCase(Builder builder) {
        super(builder);
        this.useFor = builder.useFor;
    }

    public String getUseFor() {
        return useFor;
    }

    public void setUseFor(String useFor) {
        this.useFor = useFor;
    }

    public static class Builder extends Product.Builder<Builder> {
        private String useFor;

        public Builder setUseFor(String useFor) {
            this.useFor = useFor;
            return this;
        }

        @Override
        public MobileCase build() {
            return new MobileCase(this);
        }
    }

    @Override
    public String toString() {
        return super.toString() +
                "MobileCase{" +
                "useFor='" + useFor + '\'' +
                '}';
    }

    @Override
    public String toCSV() {
        return super.toCSV() + "," + useFor;
    }
}
