package comchatory.rainvic.ccityhkchatadmin.viewUtil;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/* <p/>
 * If you extend this Adapter you are able to add a Header, a Footer or both
 * by a similar ViewHolder pattern as in RecyclerView.
 * <p/>
 * If you want to omit changes to your class hierarchy you can try the Plug-and-Play
 * approach HeaderRecyclerViewAdapterV1.
 * <p/>
 * Don't override (Be careful while overriding)
 * - onCreateViewHolder
 * - onBindViewHolder
 * - getItemCount
 * - getItemViewType
 * <p/>
 * You need to override the abstract methods introduced by this class. This class
 * is not using generics as RecyclerView.Adapter make yourself sure to cast right.
 * <p/>
 * TOTALLY UNTESTED - USE WITH CARE - HAVE FUN :)
 */

/*
* Lok: remove the inner static VH class
*      change bindholder parent class to RecyclerView.ViewHolder:
*         public class DataBindingViewHolder extends RecyclerView.ViewHolder
*
*/
public abstract class HeaderRecyclerViewAdapterV2<VH extends RecyclerView.ViewHolder>
       extends RecyclerView.Adapter<VH>{
   public static final int TYPE_HEADER = Integer.MIN_VALUE;
   public static final int TYPE_FOOTER = Integer.MIN_VALUE + 1;
   public static final int TYPE_ADAPTEE_OFFSET = 2;

//    private static final int ITEM_ANIM_DURATION = 300;
//    private static final int ITEM_ANIM_DELAY = 50;
//
//    public static final int FROM_BOTTOM = 0;
//    public static final int FROM_RIGHT = 1;
//    // Allows to remember the last item shown on screen
//    private int lastPosition = -1;
//    private int animationStartPosition = 0;
//    private int animationDirection = FROM_BOTTOM;



    @Override
   public VH onCreateViewHolder(ViewGroup parent, int viewType) {
       if (viewType == TYPE_HEADER) {
           return onCreateHeaderViewHolder(parent, viewType);
       } else if (viewType == TYPE_FOOTER) {
           return onCreateFooterViewHolder(parent, viewType);
       }
       return onCreateBasicItemViewHolder(parent, viewType - TYPE_ADAPTEE_OFFSET);
   }

   @Override
   public void onBindViewHolder(VH holder, int position) {
       if (position == 0 && holder.getItemViewType() == TYPE_HEADER) {
           onBindHeaderView(holder, position);
       } else if (position == (getBasicItemCount()+(useHeader()?1:0))
               && holder.getItemViewType() == TYPE_FOOTER) {
           onBindFooterView(holder, position);
       } else {
           position = position - (useHeader() ? 1 : 0);
           if(position < 0){
               position = 0;
           }
           onBindBasicItemView(holder, position);
       }

       // Here you apply the animation when the view is bound
//       if(holder instanceof DataBindingViewHolder) {
//           setAnimation(((DataBindingViewHolder) holder).getBinding().getRoot(), position);
//       }
   }

   @Override
   public int getItemCount() {
       int itemCount = getBasicItemCount();
       if (useHeader()) {
           itemCount += 1;
       }
       if (useFooter()) {
           itemCount += 1;
       }
       return itemCount;
   }

   @Override
   public int getItemViewType(int position) {
       if (position == 0 && useHeader()) {
           return TYPE_HEADER;
       }
       if (position == (getBasicItemCount()+(useHeader()?1:0)) && useFooter()) {
           return TYPE_FOOTER;
       }
       if (getBasicItemType(position) >= Integer.MAX_VALUE - TYPE_ADAPTEE_OFFSET) {
           new IllegalStateException("HeaderRecyclerViewAdapter offsets your BasicItemType by " + TYPE_ADAPTEE_OFFSET + ".");
       }
       return getBasicItemType(position) + TYPE_ADAPTEE_OFFSET;
   }

    /**
     * Here is the key method to apply the animation
     */
//    public void setAnimation(View viewToAnimate, int position)
//    {
//        if(position == 0){
//            animationStartPosition = 0;
//        }
//        // If the bound view wasn't previously displayed on screen, it's animated
//        if (position > lastPosition)
//        {
//            Animation animation = AnimationUtils.loadAnimation(viewToAnimate.getContext(), R.anim.slide_in_bottom);
//            if(animationDirection == FROM_RIGHT){
//                animation = AnimationUtils.loadAnimation(viewToAnimate.getContext(), R.anim.slide_in_right);
//            }
//            animation.setDuration(ITEM_ANIM_DURATION);
//            animation.setInterpolator(new OvershootInterpolator(1f));
//            animation.setAnimationListener(new Animation.AnimationListener() {
//                @Override
//                public void onAnimationStart(Animation animation) {
//                }
//
//                @Override
//                public void onAnimationEnd(Animation animation) {
//                    animationStartPosition = lastPosition;
//                }
//
//                @Override
//                public void onAnimationRepeat(Animation animation) {
//                }
//            });
//
//            animation.setStartOffset((position - animationStartPosition)*ITEM_ANIM_DELAY);
//            viewToAnimate.startAnimation(animation);
//            lastPosition = position;
//        }
//    }
//
//    public void setAnimationDirection(int direction){
//        animationDirection = direction;
//    }
//
//    public int getLastPosition() {
//        return lastPosition;
//    }
//
//    public void setLastPosition(int lastPosition) {
//        this.lastPosition = lastPosition;
//    }
//
//    public int getAnimationStartPosition() {
//        return animationStartPosition;
//    }
//
//    public void setAnimationStartPosition(int animationStartPosition) {
//        this.animationStartPosition = animationStartPosition;
//    }
//
//    @Override
//    public void onViewDetachedFromWindow(VH holder) {
////        super.onViewDetachedFromWindow(holder);
//        if(holder instanceof DataBindingViewHolder) {
//            ((DataBindingViewHolder) holder).clearAnimation();
//        }
//    }

    public abstract boolean useHeader();

   public abstract VH onCreateHeaderViewHolder(ViewGroup parent, int viewType);

   public abstract void onBindHeaderView(VH holder, int position);

   public abstract boolean useFooter();

   public abstract VH onCreateFooterViewHolder(ViewGroup parent, int viewType);

   public abstract void onBindFooterView(VH holder, int position);

   public abstract VH onCreateBasicItemViewHolder(ViewGroup parent, int viewType);

   public abstract void onBindBasicItemView(VH holder, int position);

   public abstract int getBasicItemCount();

   /**
    * make sure you don't use [Integer.MAX_VALUE-1, Integer.MAX_VALUE] as BasicItemViewType
    *
    * @param position
    * @return
    */
   public abstract int getBasicItemType(int position);

    public abstract Object getBasicItem(int position);

}