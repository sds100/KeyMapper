package io.github.sds100.keymapper.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.navigation.navGraphViewModels
import io.github.sds100.keymapper.R
import io.github.sds100.keymapper.action
import io.github.sds100.keymapper.data.viewmodel.ConfigKeymapViewModel
import io.github.sds100.keymapper.databinding.FragmentTriggerAndActionsBinding

/**
 * Created by sds100 on 19/03/2020.
 */
class TriggerAndActionsFragment : Fragment() {
    private val mConfigKeymapViewModel: ConfigKeymapViewModel by navGraphViewModels(R.id.nav_config_keymap)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentTriggerAndActionsBinding>(
            inflater,
            R.layout.fragment_trigger_and_actions,
            container,
            false
        )

        binding.apply {
            binding.viewModel = mConfigKeymapViewModel

            setOnAddActionClick {
                val direction = ConfigKeymapFragmentDirections.actionConfigKeymapFragmentToChooseActionFragment()
                findNavController().navigate(direction)
            }

            subscribeActionList()
        }

        return binding.root
    }

    private fun FragmentTriggerAndActionsBinding.subscribeActionList() {
        mConfigKeymapViewModel.actionModelList.observe(viewLifecycleOwner) { actionList ->
            epoxyRecyclerViewActions.withModels {
                actionList.forEach {
                    action {
                        id(it.id)
                        model(it)

                        onRemoveClick { _ ->
                            mConfigKeymapViewModel.removeAction(it.id)
                        }
                    }
                }
            }
        }
    }
}