public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static double g=6.67e-11;
    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img)
    {
        xxPos=xP;yyPos=yP;xxVel=xV;yyVel=yV;mass=m;imgFileName=img;

    }
    public Planet(Planet p)
    {
        xxPos=p.xxPos;
        xxVel=p.xxVel;
        yyPos=p.yyPos;
        yyVel=p.yyVel;
        mass=p.mass;
        imgFileName= p.imgFileName;;
    }
    public double calcDistance(Planet a)
    {
        double x=Math.pow(this.xxPos-a.xxPos,2);
        double y=Math.pow(this.yyPos-a.yyPos,2);
        return Math.sqrt(x+y);
    }

    public  double calcForceExertedBy(Planet a)
    {
        double dis=calcDistance(a);
        return g* this.mass*a.mass/dis/dis;
    }
    public double calcForceExertedByX(Planet a)
    {
        double dis=calcDistance(a);
        double f=calcForceExertedBy(a);
        return f*(a.xxPos-this.xxPos)/dis;
    }
    public double calcForceExertedByY(Planet a)
    {
        double dis=calcDistance(a);
        double f=calcForceExertedBy(a);
        return f*(a.yyPos-this.yyPos)/dis;
    }
    public double calcNetForceExertedByX(Planet[] p)
    {
        double sum=0;
        for(Planet x:p)
        {
            if(this.equals(x))
                continue;
            sum+=calcForceExertedByX(x);
        }
        return sum;
    }
    public double calcNetForceExertedByY(Planet[] p)
    {
        double sum=0;
        for(Planet x:p)
        {
            if(this.equals(x))
                continue;
            sum+=calcForceExertedByY(x);
        }
        return sum;

    }
    public void update(double dt,double fx,double fy)
    {
        this.xxVel=this.xxVel+dt*fx/this.mass;
        this.yyVel=this.yyVel+dt*fy/this.mass;
        this.xxPos=this.xxPos+dt*this.xxVel;
        this.yyPos=this.yyPos+dt*this.yyVel;

    }
    public  void draw()
    {
        StdDraw.picture(xxPos,yyPos,"images/"+imgFileName);

    }

}
