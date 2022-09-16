// have server dump all the OSC messages it receives
s.dumpOSC(1)


OSCFunc.trace(true);

(
f = { |msg, time, addr|
    if(msg[0] != '/status.reply') {
        "time: % sender: %\nmessage: %\n".postf(time, addr, msg);
    }
};
thisProcess.addOSCRecvFunc(f);
);
