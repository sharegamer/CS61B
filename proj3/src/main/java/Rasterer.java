import java.util.HashMap;
import java.util.Map;

/**
 * This class provides all code necessary to take a query box and produce
 * a query result. The getMapRaster method must return a Map containing all
 * seven of the required fields, otherwise the front end code will probably
 * not draw the output correctly.
 */
public class Rasterer {

    public Rasterer() {
        // YOUR CODE HERE
    }

    /**
     * Takes a user query and finds the grid of images that best matches the query. These
     * images will be combined into one big image (rastered) by the front end. <br>
     *
     *     The grid of images must obey the following properties, where image in the
     *     grid is referred to as a "tile".
     *     <ul>
     *         <li>The tiles collected must cover the most longitudinal distance per pixel
     *         (LonDPP) possible, while still covering less than or equal to the amount of
     *         longitudinal distance per pixel in the query box for the user viewport size. </li>
     *         <li>Contains all tiles that intersect the query bounding box that fulfill the
     *         above condition.</li>
     *         <li>The tiles must be arranged in-order to reconstruct the full image.</li>
     *     </ul>
     *
     * @param params Map of the HTTP GET request's query parameters - the query box and
     *               the user viewport width and height.
     *
     * @return A map of results for the front end as specified: <br>
     * "render_grid"   : String[][], the files to display. <br>
     * "raster_ul_lon" : Number, the bounding upper left longitude of the rastered image. <br>
     * "raster_ul_lat" : Number, the bounding upper left latitude of the rastered image. <br>
     * "raster_lr_lon" : Number, the bounding lower right longitude of the rastered image. <br>
     * "raster_lr_lat" : Number, the bounding lower right latitude of the rastered image. <br>
     * "depth"         : Number, the depth of the nodes of the rastered image <br>
     * "query_success" : Boolean, whether the query was able to successfully complete; don't
     *                    forget to set this to true on success! <br>
     */
    public Map<String, Object> getMapRaster(Map<String, Double> params) {
        // System.out.println(params);
        Map<String, Object> results = new HashMap<>();
        double lrlon=params.get("lrlon");
        double ullon=params.get("ullon");
        double w=params.get("w");
        double h=params.get("h");
        double lrlat=params.get("lrlat");
        double ullat=params.get("ullat");
        String[][] render_grid;
        double raster_ul_lon;
        double raster_ul_lat;
        double raster_lr_lon;
        double raster_lr_lat;
        int depth=0;
        boolean query_success;
        int xlow=0,xup=0,ylow=0,yup=0;
        double pixel=(lrlon-ullon)/w;
        double biggestpixel=(MapServer.ROOT_LRLON-MapServer.ROOT_ULLON)/MapServer.TILE_SIZE;
        for(;biggestpixel>pixel;depth++)
        {
            biggestpixel=biggestpixel/2;
        }
        if(depth>7)
        {
            depth=7;
        }
         double edgelon=MapServer.ROOT_LRLON-MapServer.ROOT_ULLON,edgelat=MapServer.ROOT_ULLAT-MapServer.ROOT_LRLAT;
        int edgenum=1;
        for(int i=depth;i>0;i--)
        {
            edgelon/=2;
            edgelat/=2;
            edgenum*=2;
        }
        for (int j=0;j<edgenum;j++)
        {
            if(MapServer.ROOT_ULLON>ullon)
                xlow=0;
            if(MapServer.ROOT_LRLON<lrlon)
                xup=edgenum-1;
            if(MapServer.ROOT_ULLON+j*edgelon<ullon && MapServer.ROOT_ULLON+(j+1)*edgelon>ullon)
                xlow=j;
            if(MapServer.ROOT_ULLON+j*edgelon<lrlon && MapServer.ROOT_ULLON+(j+1)*edgelon>lrlon)
                xup=j;
        }
        for (int j=0;j<edgenum;j++)
        {
            if(MapServer.ROOT_ULLAT<ullat)
                ylow=0;
            if(MapServer.ROOT_LRLAT>lrlat)
                yup=edgenum-1;
            if(MapServer.ROOT_ULLAT-j*edgelat>ullat && MapServer.ROOT_ULLAT-(j+1)*edgelat<ullat)
                ylow=j;
            if(MapServer.ROOT_ULLAT-j*edgelat>lrlat && MapServer.ROOT_ULLAT-(j+1)*edgelat<lrlat)
                yup=j;
        }
        raster_ul_lon=MapServer.ROOT_ULLON+xlow*edgelon;
        raster_lr_lon=MapServer.ROOT_ULLON+(xup+1)*edgelon;
        raster_ul_lat=MapServer.ROOT_ULLAT-ylow*edgelat;
        raster_lr_lat=MapServer.ROOT_ULLAT-(yup+1)*edgelat;
        render_grid=new String[yup-ylow+1][xup-xlow+1];
        System.out.println(xlow);
        System.out.println(xup);
        System.out.println(ylow);
        System.out.println(yup);

        for(int j=ylow,y=0;j<=yup;y++,j++)
            for(int i=xlow,x=0;i<=xup;i++,x++)
            {
                render_grid[y][x]="d"+depth+"_x"+i+"_y"+j+".png";
            }
        query_success=true;
        results.put("render_grid",render_grid);
        results.put("raster_ul_lon",raster_ul_lon);
        results.put("raster_ul_lat",raster_ul_lat);
        results.put("raster_lr_lon",raster_lr_lon);
        results.put("raster_lr_lat",raster_lr_lat);
        results.put("depth",depth);
        results.put("query_success",query_success);



        return results;


    }

}
