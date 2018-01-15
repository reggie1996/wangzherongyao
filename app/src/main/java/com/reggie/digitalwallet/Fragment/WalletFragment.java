package com.reggie.digitalwallet.Fragment;


import android.content.Context;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.WindowInsetsCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.reggie.digitalwallet.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.mrapp.android.tabswitcher.DragGesture;
import de.mrapp.android.tabswitcher.Layout;
import de.mrapp.android.tabswitcher.PullDownGesture;
import de.mrapp.android.tabswitcher.SwipeGesture;
import de.mrapp.android.tabswitcher.Tab;
import de.mrapp.android.tabswitcher.TabSwitcher;
import de.mrapp.android.tabswitcher.TabSwitcherDecorator;

/**
 * A simple {@link Fragment} subclass.
 */
public class WalletFragment extends BaseFragment {

    @BindView(R.id.bt_show_wallet_list)
    Button mBtShowWalletList;
    private TabSwitcher tabSwitcher;

    private static final String VIEW_TYPE_EXTRA = WalletFragment.class.getName() + "::ViewType";
    private View view;
    private Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wallet, container, false);
        tabSwitcher = view.findViewById(R.id.tab_switcher);

        tabSwitcher.setDecorator(new Decorator());
        for (int i = 0; i < 5; i++) {
            tabSwitcher.addTab(createTab(i));
        }

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected View initView() {

        return null;
    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.bt_show_wallet_list)
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.bt_show_wallet_list:
                tabSwitcher.toggleSwitcherVisibility();
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private class Decorator extends TabSwitcherDecorator {

        @NonNull
        @Override
        public View onInflateView(@NonNull final LayoutInflater inflater,
                                  @Nullable final ViewGroup parent, final int viewType) {
            View view;
            view = inflater.inflate(R.layout.tab_text_view, parent, false);

            /*
            Toolbar toolbar = view.findViewById(R.id.toolbar);
            toolbar.inflateMenu(R.menu.tab);
            toolbar.setOnMenuItemClickListener(createToolbarMenuListener());
            Menu menu = toolbar.getMenu();
            TabSwitcher.setupWithMenu(tabSwitcher, menu, createTabSwitcherButtonListener());
            */
            return view;
        }

        @Override
        public void onShowTab(@NonNull final Context context,
                              @NonNull final TabSwitcher tabSwitcher, @NonNull final View view,
                              @NonNull final Tab tab, final int index, final int viewType,
                              @Nullable final Bundle savedInstanceState) {
            //          TextView textView = findViewById(android.R.id.title);
//            textView.setText(tab.getTitle());

            //Toolbar toolbar = findViewById(R.id.toolbar);
            //toolbar.setVisibility(tabSwitcher.isSwitcherShown() ? View.GONE : View.VISIBLE);

            if (viewType != 0) {
                EditText editText = findViewById(android.R.id.edit);

                if (savedInstanceState == null) {
                    editText.setText(null);
                }

                editText.requestFocus();
            }
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public int getViewType(@NonNull final Tab tab, final int index) {
            Bundle parameters = tab.getParameters();
            return parameters != null ? parameters.getInt("1") : 0;
        }

    }

    @NonNull
    private Tab createTab(final int index) {
        CharSequence title = getString(R.string.tab_title, index + 1);
        Tab tab = new Tab(title);
        Bundle parameters = new Bundle();
        parameters.putInt(VIEW_TYPE_EXTRA, index % 2);
        tab.setParameters(parameters);
        return tab;
    }


}
