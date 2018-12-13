package cordova-plugin-walkme-sdk;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class echoes a string called from JavaScript.
 */
public class WalkMeSDK extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("start")) {
            this.start(args, callbackContext);
            return true;
        }
        else if (action.equals("restart")) {
            this.restart(callbackContext);
            return true;
        }
        else if (action.equals("sendGoal")) {
            this.sendGoal(args, callbackContext);
            return true;
        }
        else if (action.equals("setUserAttribute")) {
            this.setUserAttribute(args, callbackContext);
            return true;
        }
        else if (action.equals("setUserAttributes")) {
            this.setUserAttributes(args, callbackContext);
            return true;
        }
        else if (action.equals("setPrivateUserAttribute")) {
            this.setPrivateUserAttribute(args, callbackContext);
            return true;
        }
        else if (action.equals("setPrivateUserAttributes")) {
            this.setPrivateUserAttributes(args, callbackContext);
            return true;
        }
        else if (action.equals("clearPrivateUserAttributes")) {
            this.clearPrivateUserAttributes(callbackContext);
            return true;
        }
        else if (action.equals("setFlag")) {
            this.setFlag(args, callbackContext);
            return true;
        }
        else if (action.equals("trigger")) {
            this.trigger(args, callbackContext);
            return true;
        }
        else if (action.equals("setUserID")) {
            this.setUserID(args, callbackContext);
            return true;
        }

        return false;
    }

    private void start(JSONArray args, CallbackContext callbackContext) {
        if (args.length < 2) {
            callbackContext.error("missing params.");
            return;
        }

        String key = args.optString(0);
        String secret = args.optString(0);

        if (key != null && secret != null) {
            Context context = this.cordova.getActivity().getApplicationContext();
            ABBI.start(key, secret,  context);
            callbackContext.success(null);
        } else {
            callbackContext.error("missing params.");
        }
    }

    private void restart(CallbackContext callbackContext) {
        ABBI.restart();
        callbackContext.success(null);
    }

    private void sendGoal(JSONArray args, CallbackContext callbackContext) {
        if (args.length == 0) {
            callbackContext.error("missing params.");
            return;
        }

        String goalName = args.optString(0);
        if (args.length == 1) {
            ABBI.sendGoal(goalName);
            callbackContext.success(null);
        }
        else if (args.length == 2) {
            JSONObject object = args.optJSONObject(1);
            Map properties = toMap(object);
            ABBI.sendGoal(goalName, properties);
            callbackContext.success(null);
        }
    }

    private void setUserAttribute(JSONArray args, CallbackContext callbackContext) {
        if (args.length < 2) {
            callbackContext.error("missing params.");
            return;
        }

        String key = args.optString(0);
        Object value = args.optObject(1);

        if (key != null && value != null) {
            ABBI.setUserAttribute(key, value);
            callbackContext.success(null);
        } else {
            callbackContext.error("missing params.");
        }
    }

    private void setUserAttributes(JSONArray args, CallbackContext callbackContext) {
        if (args.length < 1) {
            callbackContext.error("missing params.");
            return;
        }

        JSONObject object = args.optJSONObject(0);
        Map attributes = toMap(object);

        if (key != null && value != null) {
            ABBI.setUserAttributes(attributes);
            callbackContext.success(null);
        } else {
            callbackContext.error("missing params.");
        }
    }

    private void setPrivateUserAttribute(JSONArray args, CallbackContext callbackContext) {
        if (args.length < 2) {
            callbackContext.error("missing params.");
            return;
        }

        String key = args.optString(0);
        Object value = args.optObject(1);

        if (key != null && value != null) {
            ABBI.setPrivateUserAttribute(key, value);
            callbackContext.success(null);
        } else {
            callbackContext.error("missing params.");
        }
    }

    private void setPrivateUserAttributes(JSONArray args, CallbackContext callbackContext) {
        if (args.length < 1) {
            callbackContext.error("missing params.");
            return;
        }

        JSONObject object = args.optJSONObject(0);
        Map attributes = toMap(object);

        if (key != null && value != null) {
            ABBI.setPrivateUserAttributes(attributes);
            callbackContext.success(null);
        } else {
            callbackContext.error("missing params.");
        }
    }

    private void clearPrivateUserAttributes(CallbackContext callbackContext) {
        ABBI.clearPrivateUserAttributes();
        callbackContext.success(null);
    }

    private void setFlag(JSONArray args, CallbackContext callbackContext) {
        if (args.length < 1) {
            callbackContext.error("missing params.");
            return;
        }

        int flag = args.optInt(0);
        ABBI.setFlag(flag);
        callbackContext.success(null);
    }

    private void trigger(JSONArray args, CallbackContext callbackContext) {
        if (args.length == 0) {
            callbackContext.error("missing params.");
            return;
        }

        String trigger = args.optString(0);
        if (args.length == 1) {
            ABBI.trigger(trigger);
            callbackContext.success(null);
        }
        else if (args.length == 2) {
            String deepLink = args.optString(0);
            ABBI.trigger(trigger, deepLink);
            callbackContext.success(null);
        }
    }

    private void setUserID(CallbackContext callbackContext) {
        if (args.length < 1) {
            callbackContext.error("missing params.");
            return;
        }

        String userId = args.optString(0);
        ABBI.setUserID(userId);
        callbackContext.success(null);
    }

    // Helpers
    private Map<String, Object> toMap(JSONObject object) {
      Map<String, Object> map = new HashMap<String, Object>();

      try {
        Iterator<String> keysItr = object.keys();
        while (keysItr.hasNext()) {
            String key = keysItr.next();
            Object value = object.get(key);

            if (value instanceof JSONArray) {
                value = toList((JSONArray) value);
            }
            else if (value instanceof JSONObject) {
                value = toMap((JSONObject) value);
            }
            map.put(key, value);
        }
      } catch (Exception e) {}

      return map;
    }

    private List<Object> toList(JSONArray array) {
      List<Object> list = new ArrayList<Object>();
        try {
          for (int i = 0; i < array.length(); i++) {
              Object value = array.get(i);
              if (value instanceof JSONArray) {
                  value = toList((JSONArray) value);
              }
              else if (value instanceof JSONObject) {
                  value = toMap((JSONObject) value);
              }

              list.add(value);
          }
        } catch (Exception e) {}

    return list;
}
}
