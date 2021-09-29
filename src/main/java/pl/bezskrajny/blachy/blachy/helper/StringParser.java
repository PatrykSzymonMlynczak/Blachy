package pl.bezskrajny.blachy.blachy.helper;

import org.springframework.stereotype.Service;

@Service
public class StringParser {

    public Parameters parseStringValue(String parameters){
        StringBuilder stringBuilder = new StringBuilder(parameters);
        String[] params = new String[3];

        int[] index = new int[2];
        int k =0;
        for(int i=0; i<parameters.length(); i++){
            if(parameters.charAt(i) == 'x'){
                index[k] = i;
                k++;
            }
        }

        int zeroPoint = 0;
        for (int i =0;i<3;i++){
            if(i==2) {
                params[i] = stringBuilder.substring(zeroPoint, parameters.length());
            }
            else {
                params[i] = stringBuilder.substring(zeroPoint, index[i]);
                zeroPoint = index[i] + 1;
                stringBuilder = new StringBuilder(parameters);
            }
        }

        return new Parameters(
                Integer.parseInt(params[0]),
                Integer.parseInt(params[1]),
                Integer.parseInt(params[2]));
    }
}
