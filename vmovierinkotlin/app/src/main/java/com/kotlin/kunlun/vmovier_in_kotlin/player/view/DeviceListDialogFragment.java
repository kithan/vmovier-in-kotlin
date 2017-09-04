///*
// * Copyright (C) 2014 Kevin Shen
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// * http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//package com.kotlin.kunlun.vmovier_in_kotlin.player.view;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.support.v4.app.DialogFragment;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.ImageView;
//import android.widget.ListView;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.example.kunlun.R;
//import com.example.kunlun.dlna.UpnpActivity;
//import com.example.kunlun.dlna.upnp.SystemManager;
//import com.jakewharton.rxbinding2.widget.RxAdapterView;
//import com.vise.log.ViseLog;
//
//import org.fourthline.cling.model.meta.Device;
//
//import java.util.Collection;
//import java.util.concurrent.TimeUnit;
//
//import io.reactivex.Observable;
//import io.reactivex.android.schedulers.AndroidSchedulers;
//import io.reactivex.disposables.Disposable;
//import io.reactivex.functions.Consumer;
//import io.reactivex.functions.Function;
//import io.reactivex.schedulers.Schedulers;
//
//public class DeviceListDialogFragment extends DialogFragment {
//    private static final String TAG = DeviceListDialogFragment.class.getSimpleName();
//    private ArrayAdapter<Device> mArrayAdapter;
//
//    public static DeviceListDialogFragment newInstance() {
//        DeviceListDialogFragment fragment = new DeviceListDialogFragment();
//        return fragment;
//    }
//
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.list_device, container, false);
//        getDialog().setTitle("Select Active Device");
//
//        ListView listView = (ListView) view.findViewById(android.R.id.list);
//
//        mArrayAdapter = new DeviceListAdapter(getActivity(), 0);
//        listView.setAdapter(mArrayAdapter);
//        RxAdapterView.itemClicks(listView).subscribe(new Consumer<Integer>() {
//            @Override
//            public void accept(Integer integer) throws Exception {
//                Device device = mArrayAdapter.getItem(integer);
//                SystemManager.getInstance().setSelectedDevice(device);
//                Intent upnpIntent = new Intent(getActivity(), UpnpActivity.class);
//                upnpIntent.putExtra("title", device.getDetails().getFriendlyName());
//                startActivity(upnpIntent);
//                dismiss();
//            }
//        });
//
//        return view;
//    }
//
//
//    Disposable intervalDisposable;
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        if (intervalDisposable != null && !intervalDisposable.isDisposed()) {
//            intervalDisposable.dispose();
//        }
//        intervalDisposable = Observable.interval(3, TimeUnit.SECONDS, Schedulers.io())
//                .map(new Function<Long, Collection<Device>>() {
//                    @Override
//                    public Collection<Device> apply(Long aLong) throws Exception {
//                        ViseLog.i("search devices");
//                        SystemManager.getInstance().searchAllDevices();
//                        Collection<Device> devices = SystemManager.getInstance().getDmrDevices();
//                        return devices;
//                    }
//                })
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Collection<Device>>() {
//                    @Override
//                    public void accept(Collection<Device> devices) throws Exception {
//                        if (devices.isEmpty()) {
//                            Toast.makeText(getActivity(), "暂未发现可用设备", Toast.LENGTH_SHORT).show();
//                            dismiss();
//                        } else {
//                            mArrayAdapter.clear();
//                            mArrayAdapter.addAll(devices);
//                        }
//                        ViseLog.i("Device list update.");
//                    }
//                });
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//
//        if (intervalDisposable != null && !intervalDisposable.isDisposed()) {
//            intervalDisposable.dispose();
//            intervalDisposable = null;
//        }
//    }
//
//
//    private class DeviceListAdapter extends ArrayAdapter<Device> {
//        private LayoutInflater mInflater;
//
//        public DeviceListAdapter(Context context, int resource) {
//            super(context, resource);
//            mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            if (convertView == null)
//                convertView = mInflater.inflate(R.layout.item_device, null);
//
//            Device item = getItem(position);
//            if (item == null) {
//                return convertView;
//            }
//
//            ImageView imageView = (ImageView) convertView.findViewById(R.id.listview_item_image);
//            imageView.setBackgroundResource(R.drawable.ic_action_dock);
//
//            TextView textView = (TextView) convertView.findViewById(R.id.listview_item_line_one);
//            textView.setText(item.getDetails().getFriendlyName());
//            return convertView;
//        }
//    }
//}
