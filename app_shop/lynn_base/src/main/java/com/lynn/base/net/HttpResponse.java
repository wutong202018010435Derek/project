package com.lynn.base.net;

import java.io.Serializable;


    public class HttpResponse<T> extends RuseltBean implements Serializable {

        private T data;

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }
}
