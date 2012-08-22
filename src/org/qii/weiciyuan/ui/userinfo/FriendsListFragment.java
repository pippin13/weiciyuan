package org.qii.weiciyuan.ui.userinfo;

import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import org.qii.weiciyuan.R;
import org.qii.weiciyuan.bean.UserListBean;
import org.qii.weiciyuan.dao.user.FriendListDao;
import org.qii.weiciyuan.ui.Abstract.IToken;

/**
 * User: Jiang Qi
 * Date: 12-8-16
 */
public class FriendsListFragment extends AbstractUserListFragment {


    public FriendsListFragment(String uid) {
        super(uid);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        if (!TextUtils.isEmpty(currentUser.getFriends_count())) {
            String number = bean.getUsers().size() + "/" + currentUser.getFriends_count();
            menu.findItem(R.id.statusesbyidtimelinefragment_status_number).setTitle(number);
        }
    }


    @Override
    protected UserListBean getDoInBackgroundNewData() {
        FriendListDao dao = new FriendListDao(((IToken) getActivity()).getToken(), uid);

        if (getList().getUsers().size() > 0) {
            dao.setCursor(bean.getPrevious_cursor());
        }
        UserListBean result = dao.getGSONMsgList();

        return result;
    }

    @Override
    protected UserListBean getDoInBackgroundOldData() {
        FriendListDao dao = new FriendListDao(((IToken) getActivity()).getToken(), uid);
        if (getList().getUsers().size() > 0) {
            dao.setCursor(bean.getNext_cursor());
        }
        UserListBean result = dao.getGSONMsgList();

        return result;
    }
}
