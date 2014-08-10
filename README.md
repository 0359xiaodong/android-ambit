# Android-Ambit


The goal of this repo is to provide a tool to communicate with an 
Ambit(ambitlib) based Suunto Watch.

### Current status:
- Detect a hardcoded device and request permission
- System launches App when device is connected
- as access is granted by the system we "only" have to talk with the watch -> openambit shows how to do this

### TODO:
- cleanup&update readme
- port ambitlib and use natively, or rewrite it into Java (my 
preference, tbd)
- first milestone: read device info and logbook

## Building

This project depends on melogsta and android-cardui (in my repos)


## Thnx

Thnx will have to go to https://github.com/openambitproject/openambit, as soon as this works ;)


## Screens

![Step1: permission granted](/screens/permission_granted.png?raw=true "Step1: Permission granted")
