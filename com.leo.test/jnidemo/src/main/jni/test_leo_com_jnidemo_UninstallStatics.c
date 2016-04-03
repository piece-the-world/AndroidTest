//
// Created by leo on 16-3-22.
//
#include <android/log.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/inotify.h>
#include "test_leo_com_jnidemo_UninstallStatics.h"

#define DEBUG(msg) __android_log_write(ANDROID_LOG_INFO,"uninstall",msg)
#define INFO(msg) __android_log_write(ANDROID_LOG_INFO,"uninstall",msg)

static char c_TAG[] = "uninstall";
static jboolean isCopy = JNI_TRUE;

JNIEXPORT void JNICALL
Java_test_leo_com_jnidemo_UninstallStatics_watchUninstallEvent(JNIEnv *env, jclass jc) {
    jstring tag = (*env)->NewStringUTF(env, c_TAG);

    DEBUG("init ok");

    pid_t pid = fork();
    if (pid < 0) {
        DEBUG("fork failed !!!");
    } else if (pid == 0) {
        DEBUG("child process-->");
        int fileDescriptor = inotify_init();
        if (fileDescriptor < 0) {
            DEBUG("inotify_init failed !!!");
            _exit(1);
        }
        int watchDescriptor;
        watchDescriptor = inotify_add_watch(fileDescriptor, "/data/data/test.leo.com.jnidemo",
                                            IN_DELETE);
        if (watchDescriptor < 0) {
            if (fileDescriptor < 0) {
                DEBUG("inotify_add_watch failed !!!");
                _exit(1);
            }
        }

        void *p_buf = malloc(sizeof(struct inotify_event));
        if (p_buf == NULL) {
            DEBUG("malloc failed !!!");
        }
        DEBUG("start observer");
        size_t readBytes = read(fileDescriptor, p_buf, sizeof(struct inotify_event));

        free(p_buf);
        inotify_rm_watch(fileDescriptor, IN_DELETE);
        DEBUG("uninstalled");

        execlp("am", "am", "start", "--user", "0", "-a", "android.intent.action.VIEW", "-d",
               "http://www.baidu.com", (char *) NULL);
    } else {
        INFO("father process-->");
    }
}
