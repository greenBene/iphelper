package click.benedikt.iphelper;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import com.maxmind.geoip2.record.Country;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

public class IPUtil {

    private DatabaseReader reader;

    public IPUtil() throws IOException {
        this(App.PATH_TO_DB);
    }

    public IPUtil(String pathToDB) throws IOException {
        // A File object pointing to your GeoIP2 or GeoLite2 database
        File database = new File("./data/GeoLite2-City.mmdb");

        // This creates the DatabaseReader object. To improve performance, reuse
        // the object across lookups. The object is thread-safe.
        reader = new DatabaseReader.Builder(database).build();
    }

    public String getCountry(String ip) throws IOException, GeoIp2Exception {
        InetAddress ipAddress = InetAddress.getByName(ip);
        CityResponse response = reader.city(ipAddress);
        Country country = response.getCountry();
        return country.getNames().get(App.LANG);
    }



}
