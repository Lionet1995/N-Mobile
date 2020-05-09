using System;
using System.Collections;

using Android.Content;
using Android.Graphics.Drawables;
using Android.Runtime;
using Android.Util;
using Android.Views;
using Android.Widget;

namespace DogsWidget
{
    [Register("dogsWidget.android.view.DogsRatingView")]
    public class DogsRatingView : LinearLayout
    {
        int currentRating;
        public ArrayList imageButtons;
        bool isChecked;

        Drawable empty;
        Drawable fill;
        Drawable highlighted;

        public DogsRatingView(Context context) :
            base(context)
        {
            Initialize(context);
        }

        public DogsRatingView(Context context, IAttributeSet attrs) :
            base(context, attrs)
        {
            currentRating = 0;
            isChecked = false;
            imageButtons = new ArrayList();

            Initialize(context, attrs);
        }

        private void Initialize(Context context, IAttributeSet attrs = null)
        {
            Inflate(context, Resource.Layout.layout_dog, this);
            // InitLayoutProperties();
            InitAttrProperties(context, attrs);
            //LayoutInflater inflater = LayoutInflater.FromContext(context);
        }

        private void InitAttrProperties(Context context, IAttributeSet attrs)
        {
            if (context == null || attrs == null)
            {
                return;
            }

            Android.Content.Res.TypedArray typedArray = context.ObtainStyledAttributes(attrs, Resource.Styleable.ItemRating);
            empty = typedArray.GetDrawable(Resource.Styleable.ItemRating_emptyDog);
            fill = typedArray.GetDrawable(Resource.Styleable.ItemRating_filledDog);
            highlighted = typedArray.GetDrawable(Resource.Styleable.ItemRating_highlightedDog);
            var btn = FindViewById<ImageButton>(Resource.Id.imageButton1);
            btn.Click += Btn_Click;
            btn.SetImageDrawable(empty);

            imageButtons.Add(btn);

            var btn2 = FindViewById<ImageButton>(Resource.Id.imageButton2);
            btn2.Click += Btn_Click;
            btn2.SetImageDrawable(empty);

            imageButtons.Add(btn2);

            var btn3 = FindViewById<ImageButton>(Resource.Id.imageButton3);
            btn3.Click += Btn_Click;
            btn3.SetImageDrawable(empty);

            imageButtons.Add(btn3);

            var btn4 = FindViewById<ImageButton>(Resource.Id.imageButton4);
            btn4.Click += Btn_Click;
            btn4.SetImageDrawable(empty);

            imageButtons.Add(btn4);

            var btn5 = FindViewById<ImageButton>(Resource.Id.imageButton5);
            btn5.Click += Btn_Click;
            btn5.SetImageDrawable(empty);

            imageButtons.Add(btn5);
        }

        private void Btn_Click(object sender, EventArgs e)
        {

            int count = 0;
            foreach (var imageButton in imageButtons)
            {
                count++;
                if (imageButton.Equals(sender)) break;
            }

            if (isChecked && count < currentRating)
            {
                for (int i = 0; i < count; i++)
                {
                    ImageButton btn1 = (ImageButton)imageButtons[i];
                    btn1.SetImageDrawable(fill);
                }
                for (int i = count; i < imageButtons.Count; i++)
                {
                    ImageButton btn1 = (ImageButton)imageButtons[i];
                    btn1.SetImageDrawable(empty);
                }

                isChecked = true;
            }

            else if (isChecked && count > currentRating)
            {
                if (count == imageButtons.Count)
                {
                    for (int i = 0; i < count; i++)
                    {
                        ImageButton btn1 = (ImageButton)imageButtons[i];
                        btn1.SetImageDrawable(highlighted);
                    }
                }
                else
                {
                    for (int i = 0; i < count; i++)
                    {
                        ImageButton btn1 = (ImageButton)imageButtons[i];
                        btn1.SetImageDrawable(fill);
                    }
                }

                isChecked = true;
            }

            else if (isChecked == false)
            {
                if (count == imageButtons.Count)
                {
                    for (int i = 0; i < count; i++)
                    {
                        ImageButton btn1 = (ImageButton)imageButtons[i];
                        btn1.SetImageDrawable(highlighted);
                    }
                }
                else
                {
                    for (int i = 0; i < count; i++)
                    {
                        ImageButton btn1 = (ImageButton)imageButtons[i];
                        btn1.SetImageDrawable(fill);
                    }
                }
                isChecked = true;
            }
            else if (isChecked && count == currentRating)
            {
                for (int i = 0; i < count; i++)
                {
                    ImageButton btn1 = (ImageButton)imageButtons[i];
                    btn1.SetImageDrawable(empty);
                }
                isChecked = false;
            }

            currentRating = count;
        }

        private void InitLayoutProperties()
        {
            SetGravity(GravityFlags.CenterVertical);
            Orientation = Orientation;
        }
    }
}