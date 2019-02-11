#import <Cordova/CDV.h>
#import <WalkMeSDK/WalkMeSDK.h>

@interface WalkMeSDK : CDVPlugin {}

/**
 * Starts ABBI SDK.
 *
 * @param appId The Application Id provided by WalkMe
 * @param appSecretKey The Application Secret key provided by WalkMe
 *
 * To get your Application Id and Application Secret key, login to WalkMe console at https://console.mobile.walkme.com
 * and click the settings icon near your Application Name. You can find more info here - https://walkme-mobile.zendesk.com/hc
 */
- (void)start:(CDVInvokedUrlCommand *)command;

/**
 * Restarts a new SDK session
 *
 */
 - (void)restart:(CDVInvokedUrlCommand *)command;

 /**
  * Sends a Goal to ABBI's Backend.
  * A Goal is a user action that can be used to target your users.
  *
  * Usage Example :@
  *
  * [ABBI sendGoal:@"Bought a blue sword" withProperites:nil]
  * [ABBI sendGoal:@"Bought a blue sword" withProperites:@{@"item_name", @"unlimited_calls"}]
  *
  * @param goalName the Goal name.
  * @param properties the Goal properties, key-value structured (if any).
  */
 - (void)sendGoal:(CDVInvokedUrlCommand *)command;

 /**
  * Sets a user attribute
  *
  * @param key the attribute key
  * @param value the attribute value
  *
  * @code
  * Usage Examples:
  * [ABBI setUserAttributeWithKey:@"isProUser" andValue:@YES];
  * [ABBI setUserAttributeWithKey:@"isLoggedIn" andValue:@(0)];
  */
- (void)setUserAttribute:(CDVInvokedUrlCommand *)command;

/**
 * Sets multiple user attributes
 *
 * @param attributes the user attributes
 *
 * @code
 * Usage Example:
 * [ABBI setUserAttributes:@{@"isProUser": @YES, @"isLoggedIn": @(0)}];
 */
- (void)setUserAttributes:(CDVInvokedUrlCommand *)command;

/**
 * Sets a private user attribute
 *
 * @param key the attribute key
 * @param value the attribute value
 *
 * @code
 * Usage Examples:
 * [ABBI setPrivateUserAttributeWithKey:@"gender" andValue:@"female"];
 * [ABBI setPrivateUserAttributeWithKey:@"balance" andValue:@(1000)];
 */
- (void)setPrivateUserAttribute:(CDVInvokedUrlCommand *)command;

/**
 * Sets multiple private user attributes
 *
 * @param attributes the private user attributes
 *
 * @code
 * Usage Example:
 * [ABBI setPrivateUserAttributes:@{@"gender": @"female", @"balance": @(1000)}];
 */
- (void)setPrivateUserAttributes:(CDVInvokedUrlCommand *)command;

/**
 * Clears all private user attributes
 */
- (void)clearPrivateUserAttributes:(CDVInvokedUrlCommand *)command;

/**
 * Utility function for remote sessions with ABBI support team.
 *
 * @param n Will be given by ABBI support team when needed
 */
- (void)setFlag:(CDVInvokedUrlCommand *)command;

/**
 * Launches a campaign by trigger key after redirecting the user to the given deep link
 * Once invoked, the method will show the campaign WITHOUT any of its segments (if defined)
 *
 * @code
 * Usage Example :
 * [ABBI trigger:@"Show How To Order Credit Card" withDeepLink:@"myapp://main_screen"];
 */
 - (void)trigger:(CDVInvokedUrlCommand *)command;

 /**
  * Sets user id
  *
  * @param userId the user id as NSString
  *
  * @code
  * Usage Example:
  * [ABBI setUserID:@"myuserid"];
  */
 - (void)setUserID:(CDVInvokedUrlCommand *)command;

@end
