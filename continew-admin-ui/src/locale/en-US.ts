import localeWorkplace from '@/views/dashboard/workplace/locale/en-US';

import localeUser from '@/views/system/user/locale/en-US';
import localeRole from '@/views/system/role/locale/en-US';
import localeMenu from '@/views/system/menu/locale/en-US';
import localeDept from '@/views/system/dept/locale/en-US';

import localeGenerator from '@/views/tool/generator/locale/en-US';

import localeOnlineUser from '@/views/monitor/online/locale/en-US';
import localeLoginLog from '@/views/monitor/log/login/locale/en-US';
import localeOperationLog from '@/views/monitor/log/operation/locale/en-US';
import localeSystemLog from '@/views/monitor/log/system/locale/en-US';

import localeSearchTable from '@/views/arco-design/list/search-table/locale/en-US';
import localeCardList from '@/views/arco-design/list/card/locale/en-US';
import localeStepForm from '@/views/arco-design/form/step/locale/en-US';
import localeGroupForm from '@/views/arco-design/form/group/locale/en-US';
import localeBasicProfile from '@/views/arco-design/profile/basic/locale/en-US';
import localeSuccess from '@/views/arco-design/result/success/locale/en-US';
import localeError from '@/views/arco-design/result/error/locale/en-US';
import locale403 from '@/views/arco-design/exception/403/locale/en-US';
import locale404 from '@/views/arco-design/exception/404/locale/en-US';
import locale500 from '@/views/arco-design/exception/500/locale/en-US';
import localeDataAnalysis from '@/views/arco-design/visualization/data-analysis/locale/en-US';
import localeMultiDAnalysis from '@/views/arco-design/visualization/multi-dimension-data-analysis/locale/en-US';
import localeMonitor from '@/views/arco-design/visualization/monitor/locale/en-US';

import localeMessageBox from '@/components/message-box/locale/en-US';
import localeLogin from '@/views/login/locale/en-US';
import localeUserCenter from '@/views/system/user/center/locale/en-US';
import localeSettings from './en-US/settings';

export default {
  'title': 'ContiNew Admin',
  'menu.dashboard': 'Dashboard',
  'menu.server.dashboard': 'Dashboard-Server',
  'menu.server.workplace': 'Workplace-Server',
  'menu.system': 'System management',
  'menu.tool': 'Tool',
  'menu.monitor': 'Monitor',
  'menu.list': 'List',
  'menu.form': 'Form',
  'menu.profile': 'Profile',
  'menu.result': 'Result',
  'menu.exception': 'Exception',
  'menu.visualization': 'Data Visualization',
  'menu.server.monitor': 'Monitor-Server',
  'menu.arcoWebsite': 'Arco Design Vue',
  'menu.github': 'GitHub',
  'menu.user': 'User Center',
  'navbar.docs': 'Docs',
  'navbar.action.locale': 'Switch to English',

  ...localeWorkplace,

  ...localeUser,
  ...localeRole,
  ...localeMenu,
  ...localeDept,

  ...localeGenerator,

  ...localeOnlineUser,
  ...localeLoginLog,
  ...localeOperationLog,
  ...localeSystemLog,

  ...localeSearchTable,
  ...localeCardList,
  ...localeStepForm,
  ...localeGroupForm,
  ...localeBasicProfile,
  ...localeSuccess,
  ...localeError,
  ...locale403,
  ...locale404,
  ...locale500,
  ...localeDataAnalysis,
  ...localeMultiDAnalysis,
  ...localeMonitor,

  ...localeMessageBox,
  ...localeLogin,
  ...localeUserCenter,
  ...localeSettings,
};
