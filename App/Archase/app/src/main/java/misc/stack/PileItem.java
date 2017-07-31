package misc.stack;

import java.io.Serializable;

/**
 * Created by rhofmann on 17.03.2016.
 */
public interface PileItem extends Serializable{
    public String getOracle();

    public String getName();

    public String getType();

    public Object getPicture();
}
