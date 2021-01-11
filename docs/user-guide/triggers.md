# Triggers

## Options

There are options for each trigger key by pressing the 3-dots on them. There are also more options for the whole trigger under the "options" tab. Here are explanations of each option.

### Trigger by Intent (v2.3.0+)

This allows you to trigger the key map by sending an [Intent](https://developer.android.com/reference/android/content/Intent) to Key Mapper. There are many apps that can automate broadcasting Intents such as Tasker and Automate.

#### Intent action

```
io.github.sds100.keymapper.TRIGGER_KEYMAP_BY_UID
```

#### Intent string extra 

```
io.github.sds100.keymapper.KEYMAP_UID
```

and the value is the UUID of the key map. You can copy the UUID in Key Mapper by turning on the "Trigger by Intent" option.

#### Intent package (optional but recommended)

```
io.github.sds100.keymapper
```

This will only send the Intent to Key Mapper and no other packages.

**<span style="color:red">Important!!!</span>**

Add `.debug` or `.ci` to the end of all `io.github.sds100.keymapper` instances in the action, extra and package if your Key Mapper build is a debug or ci build respectively.
